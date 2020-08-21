package rdd

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark37_RDD_Operate_Transform22 {

  def main(args: Array[String]): Unit = {


    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)

    // TODO Spark  - 转换算子 - Key-Value类型
    //  reduceByKey 性能比groupByKey高很多（解析见图，重要！！！！）

    val list = List(
      ("a",1),
      ("b",1),
      ("a",1),
      ("b",1)
    )

    val rdd = sc.makeRDD(list,2)

    // reduceByKey
    val wordCount: RDD[(String, Int)] = rdd.reduceByKey(_+_)
    wordCount.collect().foreach(println)

    // groupByKey
    val wordCount1: RDD[(String, Int)] = rdd.groupByKey().mapValues(_.size)
    wordCount1.collect().foreach(println)


    sc.stop()


  }

}
