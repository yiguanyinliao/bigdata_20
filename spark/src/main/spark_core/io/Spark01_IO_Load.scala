package io

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark01_IO_Load {

  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)

    val textFileRDD: RDD[String] = sc.textFile("output1")
    println(textFileRDD.collect().mkString(","))

    val objFileRDD: RDD[Int] = sc.objectFile[Int]("output2")
    println(objFileRDD.collect().mkString(","))

    val sequenceFileRDD: RDD[(Int, Int)] = sc.sequenceFile[Int,Int]("output3")
    println(sequenceFileRDD.collect().mkString(","))


    sc.stop()
  }

}
