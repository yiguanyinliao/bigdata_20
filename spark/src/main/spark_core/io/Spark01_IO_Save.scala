package io

import org.apache.spark.{SparkConf, SparkContext}

object Spark01_IO_Save {

  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)

    val rdd = sc.makeRDD(List(1,2,3,4),2)

    rdd.saveAsTextFile("output1")
    rdd.saveAsObjectFile("output2")
    rdd.map((_,1)).saveAsSequenceFile("output3")

    sc.stop()

  }

}
