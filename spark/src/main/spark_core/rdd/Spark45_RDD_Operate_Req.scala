package rdd

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark45_RDD_Operate_Req {

  def main(args: Array[String]): Unit = {


    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)

    // TODO spark 案列实操
    //  统计出每一个省份每个广告被点击数量排行的Top3

    // 1.读取日志数据，获取原始数据
    val logRDD: RDD[String] = sc.textFile("input/agent.log")

    // 2.将数据进行结构的转换，方便统计
    //    （xxx 河北省 石家庄 zs a）=> ((河北省,a),1)
    val dataRDD = logRDD.map(
      log => {
        val data: Array[String] = log.split(" ")
        ((data(1), data(4)), 1)
      }
    )

    // 3.将转换结构后的数据进行分组集合
    val dataReduceRDD: RDD[((String, String), Int)] = dataRDD.reduceByKey(_+_)
    //     ((河北省,a),1) => ((河北省,a),sum)
    // 4.将聚合后的结果进行结构的转换
    //      ((河北省,a),sum) => (河北省,(a,sum))

    //    dataReduceRDD.map(
    //      t => {
    //        (t._1._1,(t._1._2,t._2))
    //      }
    //    )
    // 使用模式匹配更加鲜明
        val dataMapRDD: RDD[(String, (String, Int))] = dataReduceRDD.map {
          case ((prv, adv), sum) => {
            (prv, (adv, sum))
          }
        }

    // 5.根据省份将转换后的数据进行分组
    val dataGroupRDD: RDD[(String, Iterable[(String, Int)])] = dataMapRDD.groupByKey()
    //      (河北省,(a,sum)) => (河北省,Iterator[(a,sum),(b,sum),(c,sum)])
    // 6.将分组后的数据根据数量进行统计排序（降序）
    // 7.根据排序后的数据获取广告点击率前3名
    val resultRDD: RDD[(String, List[(String, Int)])] = dataGroupRDD.mapValues(
      iter => {
        iter.toList.sortWith(
          (left, right) => {
            left._2 > right._2
          }
        ).take(3)
      }
    )

    // 8.将结果打印到控制台
    resultRDD.collect().foreach(println)

    sc.stop()


  }


}
