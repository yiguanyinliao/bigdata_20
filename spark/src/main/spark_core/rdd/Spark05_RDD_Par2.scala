package rdd

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark05_RDD_Par2 {

  def main(args: Array[String]): Unit = {


//    val sparkConf = new SparkConf().setMaster("local[2]").setAppName("spark")
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("spark")
    val sc = new SparkContext(sparkConf)


    // TODO 文件时间的分区
    //  textFile的第二个参数是最小分区的数量（不一定相等）
    val file = sc.textFile("input/word.txt")
    file.saveAsTextFile("output")

    sc.stop()

  }

}
