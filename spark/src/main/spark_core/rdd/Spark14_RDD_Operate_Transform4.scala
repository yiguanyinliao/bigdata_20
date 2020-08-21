package rdd

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark14_RDD_Operate_Transform4 {

  def main(args: Array[String]): Unit = {


    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)

    val list = List(1,2,3,4)
    val rdd: RDD[Int] = sc.makeRDD(list,2)
    val newRdd = rdd.mapPartitions(
      iter => {
        iter.filter(_ % 2 == 0)
      }
    )
    newRdd.collect().foreach(println)


    // TODO 小练习:获取每个分区的最大值

    val newRdd1 = rdd.mapPartitions(
      iter => {
        List(iter.max).iterator
      }
    )
    newRdd1.collect().foreach(println)


    sc.stop()

  }

}
