package req.service

import autumn_frameworker.core.TService
import autumn_frameworker.util.EnvUtil
import org.apache.spark.rdd.RDD
import req.bean.UserVisitAction
import req.dao.PageFlowDao


class PageFlowService extends TService{


  private val pageFlowDao = new PageFlowDao

  /**
    * 分析
    */
  override def analysis()= {

    // TODO 获取原始数据
    val rdd = pageFlowDao.fromFile("input/user_visit_action.txt")

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
    actionRDD.cache()

    // 只对特定的页面跳转进行统计
    val flowIDs  = List(1,2,3,4,5,6,7)
    val okFlowIds = flowIDs.zip(flowIDs.tail).map(t=> t._1 + "-" + t._2)

    // TODO 计算分母
    // 过滤数据
    val filterRDD = actionRDD.filter(action => {
//      flowIDs.contains(action.page_id.toInt)
      flowIDs.init.contains(action.page_id.toInt)
    })

    val pageToOneRDD = filterRDD.map(
      action => (action.page_id, 1)
    )
    val pageCountArray = pageToOneRDD.reduceByKey(_+_).collect()
    val pageCountMap = pageCountArray.toMap

    val pageCountBC = EnvUtil.getEnv().broadcast(pageCountMap)

    // TODO 计算分子
    // 1.将数据根据用户session进行分组
    val sessionRDD: RDD[(String, Iterable[UserVisitAction])] = actionRDD.groupBy(_.session_id)

    val mvRDD = sessionRDD.mapValues(
      iter => {
        // 2.对分组后的数据进行组内排序（时间升序）
        val actionsData = iter.toList.sortWith(
          (left, right) => {
            left.action_time < right.action_time
          }
        )
        // 3.将排序后的数据进行结构的转换
        val pageIds: List[Long] = actionsData.map(_.page_id)
        // 4.将转换结构后的数据进行数据的组合
        // [1,2,3,4] => 1-2,2-3,3-4
        // 滑动窗口、拉链都可以实现
        // [1,2,3,4]
        // [2,3,4].
        val zipIds: List[(Long, Long)] = pageIds.zip(pageIds.tail)
        zipIds.map {
          case (id1, id2) => {
            (id1 + "-" + id2, 1)
          }
        }.filter{
          case(ids,_)=>{
            okFlowIds.contains(ids)
          }
        }
      }
    )
    // List[(1-2,1),(2-3,1)] =>(1-2,1), (2-3,1)
    val mapRDD = mvRDD.map(_._2)
    // (1-2,1), (2-3,1),(1-2,1)
    val flatRDD = mapRDD.flatMap(list => list)
    // (1-2,2), (2-3,1)
    val pageFlowToSumRDD = flatRDD.reduceByKey(_+_)


    // TODO 获取页面单跳转化率
    pageFlowToSumRDD.foreach{
      case(pageFlow,sum) => {
        val pageId = pageFlow.split("-")(0)
        val total = pageCountBC.value.getOrElse(pageId.toLong,0)

        println(s"页面跳转【${pageFlow}】转化率为：" + (sum.toDouble/total))
      }
    }

  }
}
