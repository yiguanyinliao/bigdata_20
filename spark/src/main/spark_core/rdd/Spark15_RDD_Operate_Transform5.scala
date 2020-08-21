package rdd

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark15_RDD_Operate_Transform5 {

  def main(args: Array[String]): Unit = {


    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)

    val list = List(1,2,3,4)
    // TODO 获取分区数据的同时，想要获得每个数据所在的分区编号
    // 1 => (0,1)
    // 2 => (0,2)
    // 3 => (1,3)
    // 4 => (1,4)

//    val rdd = sc.makeRDD(list)
    val rdd = sc.makeRDD(list,2)
    val newRdd = rdd.mapPartitionsWithIndex(
      (index, iter) => {
        iter.map(
          num => (index, num)
        )
      }

    )
    newRdd.collect().foreach(println)

    sc.stop()

  }

}
