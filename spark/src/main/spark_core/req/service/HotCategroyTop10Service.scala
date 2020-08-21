package req.service

import autumn_frameworker.core.TService
import autumn_frameworker.util.EnvUtil
import org.apache.spark.rdd.RDD
import req.bean.HotCategory
import req.dao.HotCategroyTop10Dao
import req.helper.HotCategoryAccumulator

import scala.collection.mutable

class HotCategroyTop10Service extends TService{


  private val hotCategroyTop10Dao = new HotCategroyTop10Dao

//  override def analysis()= {
//    // 1.从用户行为数据文件中获取原始数据
//    //        dao类主要用于和数据的交互，读取数据的操作有DAO来完成.
//    val actionRDD: RDD[String] = hotCategroyTop10Dao.fromFile("input/user_visit_action.txt")
//
//    actionRDD.cache()   // 使用缓存减少内存冗余
//
//    // 2.对用户点击行为进行数量统计
//    //      （"click",count）
//    val actionClickCountRDD = actionRDD.map(
//      action => {
//        val datas = action.split("_")
//        (datas(6), 1)
//      }
//    ).filter(_._1 != "-1").reduceByKey(_ + _)
//
//    // 3.对用户下单行为进行数量统计
//    //      （"order",count）
//    val actionOrderRDD = actionRDD.map(
//      action => {
//        val datas = action.split("_")
//        (datas(8), 1)
//      }
//    ).filter(_._1 != "null")
//    val actionOrdersRDD = actionOrderRDD.flatMap {
//      case (orderid, one) => {    // 模式匹配
//        val orderids = orderid.split(",")
//        orderids.map((_, one))
//      }
//    }
//    val actionOrderCountRDD = actionOrdersRDD.reduceByKey(_+_)
//    // 4.对用户支付行为进行数量统计
//    //      （"pay",count）
//    val actionPayRDD = actionRDD.map(
//      action => {
//        val datas = action.split("_")
//        (datas(10), 1)
//      }
//    ).filter(_._1 != "null")
//    val actionPaysRDD = actionPayRDD.flatMap{
//      case ( payid, one ) => {
//        val payids = payid.split(",")
//        payids.map((_, one))
//      }
//    }
//    val actionPayCountRDD = actionPaysRDD.reduceByKey(_+_)
//    // 5.将3个统计结果连在一起
//    //      （clickCount,orderCount,payCount）
//    val joinRDD: RDD[(String, ((Int, Int), Int))] = actionClickCountRDD.join(actionOrderCountRDD).join(actionPayCountRDD)
//    val resultRDD = joinRDD.mapValues{
//      case ( (clickCount, orderCount), payCount ) => {
//        ( clickCount, orderCount, payCount )
//      }
//    }
//    // 6.将数据的结果进行排序（降序），取前10名
//    // TODO 元祖可以直接用于排序，先比较第一个值，再比较第二个值，以此类推
//    resultRDD.sortBy(_._2, false).take(10)  // 根据value来排
//
//
//  }

  /**
    * 分析
    * 1.功能
    * 2.性能
    *   2.1 原始数据 actionRDD 重复使用
    *       使用cache()
    *   2.2 Shuffle次数比较多
    *   2.3 join效率不高（笛卡尔乘机）
    *       使用union + reduceByKey
    *
    */
//  override def analysis()= {
//    val actionRDD: RDD[String] = hotCategroyTop10Dao.fromFile("input/user_visit_action.txt")
//
//    actionRDD.cache()   // 使用缓存减少内存冗余
//
//    // TODO 点击行为统计
//    val actionClickCountRDD = actionRDD.map(
//      action => {
//        val datas = action.split("_")
//        (datas(6), 1)
//      }
//    ).filter(_._1 != "-1").reduceByKey(_ + _)
//
//    // TODO 下单行为统计
//    val actionOrderRDD = actionRDD.map(
//      action => {
//        val datas = action.split("_")
//        (datas(8), 1)
//      }
//    ).filter(_._1 != "null")
//    val actionOrdersRDD = actionOrderRDD.flatMap {
//      case (orderid, one) => {    // 模式匹配
//        val orderids = orderid.split(",")
//        orderids.map((_, one))
//      }
//    }
//    val actionOrderCountRDD = actionOrdersRDD.reduceByKey(_+_)
//
//    // TODO 支付行为统计
//    val actionPayRDD = actionRDD.map(
//      action => {
//        val datas = action.split("_")
//        (datas(10), 1)
//      }
//    ).filter(_._1 != "null")
//    val actionPaysRDD = actionPayRDD.flatMap{
//      case ( payid, one ) => {
//        val payids = payid.split(",")
//        payids.map((_, one))
//      }
//    }
//    val actionPayCountRDD = actionPaysRDD.reduceByKey(_+_)
//
//    // TODO 连接数据 fold
//    //  1，2，3，4 => 10
//    //  (品类ID,clickCnt),(品类ID,orderCnt),(品类ID,payCnt)
//    //  (品类ID,(10,0,0),(品类ID,(0,20,0),(品类ID,(0,0,30))
//    //  (品类ID,(clickCnt,orderCnt,payCnt))
//    //  (品类ID,(10,20,30))
//    val newActionClickCountRDD = actionClickCountRDD.map {
//      case (categoryid, clickcnt) => {
//        (categoryid, (clickcnt, 0, 0))
//      }
//    }
//    val newActionOrderCountRDD = actionOrderCountRDD.map {
//      case (categoryid, ordercnt) => {
//        (categoryid, (0, ordercnt, 0))
//      }
//    }
//    val newActionPayCountRDD = actionPayCountRDD.map {
//      case (categoryid, paycnt) => {
//        (categoryid, (0, 0, paycnt))
//      }
//    }
//
//    // TODO 将三个不同指标的数据合并在一起，不需要key相同合并
//    val countRDD = newActionClickCountRDD.union(newActionOrderCountRDD).union(newActionPayCountRDD)
//    val resultRDD = countRDD.reduceByKey(
//      (t1, t2) => {
//        (t1._1 + t2._1, t1._2 + t2._2, t1._3 + t2._3)
//      }
//    )
//    resultRDD.sortBy(_._2, false).take(10)  // 根据value来排
//  }

  /**
    * 减少Shuffle次数，减少reduceByKey使用的次数
    *
    * 极限情况下，使用一次reduceByKey计算出来性能最高
    *
    * 1.点击数量的统计 => (品类ID,clickCnt) => (品类ID,(clickCnt,0,0))
    *   (品类ID，1) => (品类ID,(1,0,0))
    * 2.下单数量的统计 => (品类ID,orderCnt) => (品类ID,(0,orderCnt,0))   下单和支付会有多个
    *   (品类ID，1) => (品类ID-1,(0,1,0)),(品类ID-2,(0,1,0))
    * 3.支付数量的统计 => (品类ID,payCnt) => (品类ID,(0,0,payCnt))
    *   (品类ID，1) => (品类ID-1,(0,0,1)),(品类ID-2,(0,1,0))
    * 4.最终结果的统计 => (品类ID,(clickCnt，orderCnt，payCnt))
    *
    */

//  def analysis2()= {
//    val actionRDD: RDD[String] = hotCategroyTop10Dao.fromFile("input/user_visit_action.txt")
//    // 将每一条数据转换成特定的格式
//    val flatRDD: RDD[(String, (Int, Int, Int))] = actionRDD.flatMap(
//      action => {
//        val datas = action.split("_")
//        if (datas(6) != "-1") {
//          // 点击的场合
//          List((datas(6), (1, 0, 0)))
////        ((datas(6), (1, 0, 0)))
//        } else if (datas(8) != "null") {
//          // 下单的场合
//          val ids = datas(8).split(",")
//          ids.map((_, (0, 1, 0)))
//        } else if (datas(10) != null) {
//          // 支付的场合
//          val ids = datas(10).split(",")
//          ids.map((_, (0, 0, 1)))
//        } else {
//          Nil
//        }
//      }
//    )
//    val resultRDD = flatRDD.reduceByKey(
//      (t1, t2) => {
//        (t1._1 + t2._1, t1._2 + t2._2, t1._3 + t2._3)
//      }
//    )
//    resultRDD.sortBy(_._2, false).take(10)
//  }

/**
  * 累加器
 */

  override def analysis()= {
    // TODO 读取原始日志数据
    val actionRDD: RDD[String] = hotCategroyTop10Dao.fromFile("input/user_visit_action.txt")

    // TODO 创建累加器，向spark进行注册
    val acc = new HotCategoryAccumulator
    EnvUtil.getEnv().register(acc,"HotCategory")

    // TODO 遍历数据，将数据项累加器中传递
    actionRDD.foreach(
      action => {
        val datas = action.split("_")
        if (datas(6) != "-1") {
          acc.add((datas(6),"click"))
        } else if (datas(6) !="null") {
          val ids = datas(8).split(",")
          ids.foreach(
            id => {
              acc.add(id,"order")
            }
          )
        }else if (datas(10) != "null") {
          val ids = datas(10).split(",")
          ids.foreach(
            id => {
              acc.add(id,"pay")
            }
          )
        }
      }
    )
    // TODO 获取累加器的值，进行排序（降序），取前10
    val accValue: mutable.Map[String, HotCategory] = acc.value
    val categories = accValue.map(_._2)

    categories.toList.sortWith(    // Map是无序的，需要转成List
      (left,right) => {
        if (left.clickCount > right.clickCount) {
          true
        } else if(left.clickCount == right.clickCount) {
          if (left.orderCount > right.orderCount) {
            true
          }else if (left.orderCount == right.orderCount) {
            left.payCount > right.payCount
          }
          else {
            false
          }
        }
        else{
          false
        }

      }
    ).take(10)

  }


}
