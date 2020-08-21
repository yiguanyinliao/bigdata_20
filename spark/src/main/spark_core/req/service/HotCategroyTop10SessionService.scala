package req.service

import autumn_frameworker.core.TService
import autumn_frameworker.util.EnvUtil
import org.apache.spark.broadcast.Broadcast
import org.apache.spark.rdd.RDD
import req.bean.{HotCategory, UserVisitAction}
import req.dao.HotCategroyTop10SessionDao


class HotCategroyTop10SessionService extends TService{


  private val hotCategroyTop10SessionDao = new HotCategroyTop10SessionDao

  /**
    * 分析
    */
  override def analysis(data: Any)= {

    // TODO 获取TOP10热门品类
    val top10: List[HotCategory] = data.asInstanceOf[List[HotCategory]]

    // TODO 获取用户行为的数据
    val rdd = hotCategroyTop10SessionDao.fromFile("input/user_visit_action.txt")

    // TODO 将数据过滤（保留热门数据TOP10）
    //  一般处理数据时，为了处理方便，会将数据转换为Bean对象来使用.
    val actionRDD = rdd.map(
      action => {
        val datas = action.split("_")
        UserVisitAction(
          datas(0),
          datas(1).toLong,
          datas(2),
          datas(3).toLong,
          datas(4),
          datas(5),
          datas(6).toLong,
          datas(7).toLong,
          datas(8),
          datas(9),
          datas(10),
          datas(11),
          datas(12).toLong
        )
      }
    )
    actionRDD

    // TODO 将过滤后数据进行处理
    //  action => ((categoryid,session),1) => ((categoryid,session),sum)
    //  1.top10集合不需要将整个对象序列化后传递给Executor
    //    因为有闭包操作，会将整个对象序列化传递给Executor
    //    top10进行结构的转换，减少序列化后的字数
    //  2.将top10结构进行转换后，假如集合中存在大量的数据？
    //    使用广播变量让数据可以在Executor中共享，而不是冗余存在

    val ids: List[String] = top10.map(hc => hc.categoryid)
    val bc: Broadcast[List[String]] = EnvUtil.getEnv().broadcast(ids)

    val filterRDD = actionRDD.filter(
      action => {
        val categoryId: Long = action.click_category_id
        var flg = false
//        top10.foreach(         // 不传整个对象，减少序列化字数
//          hc => {
//            if (hc.categoryid == categoryId.toString) {

//        ids.foreach(           // 换成广播的方式，减少数据冗余
        bc.value.foreach(
          id => {
            if (id == categoryId.toString) {
              flg = true
            }
          }
        )
        flg
      }
    )
    val mapRDD = filterRDD.map(
      action => {
        ((action.click_category_id, action.session_id), 1)
      }
    )
    val reduceRDD: RDD[((Long, String), Int)] = mapRDD.reduceByKey(_+_)

    // TODO 将统计后的结果进行结构的转换
    //  ((categoryid,session),sum) => (categoryid,(session,sum))
    val reduceMapRDD = reduceRDD.map {
      case ((categoryid, session), sum) => {
        (categoryid, (session, sum))
      }
    }


    // TODO 将转换结构后的数据按照品类进行分组
    val groupRDD: RDD[(Long, Iterable[(String, Int)])] = reduceMapRDD.groupByKey()

    // TODO 将同一个组内的数据按照点击的数量进行排序（降序），取前10名
    val resultRDD = groupRDD.mapValues(
      iter => {
        iter.toList.sortWith(
          (left, right) => {
            left._2 > right._2
          }
        ).take(10)
      }
    )
    resultRDD

  }
}
