package rdd

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark04_RDD_Par1 {

  def main(args: Array[String]): Unit = {


    val sparkConf = new SparkConf().setMaster("local[2]").setAppName("spark")
//    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("spark")
    val sc = new SparkContext(sparkConf)


    // TODO 如果想要改变分区数，可以改变第二个参数
    val list = List(1,2,3,4)
    val num: RDD[Int] = sc.makeRDD(list,3)
    num.saveAsTextFile("output")
    sc.stop()

  }

}
