package rdd

import org.apache.spark.{SparkConf, SparkContext}

object Spark18_RDD_Operate_Transform6 {

  def main(args: Array[String]): Unit = {


    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)


    val list = List(List(1,2),List(3,4))
    val rdd = sc.makeRDD(list)
    val newRdd = rdd.flatMap(list => list)
    newRdd.collect().foreach(println)

    val list1 = List(1,2,3,4)
    val rdd1 = sc.makeRDD(list1)
    val newRdd1 = rdd1.flatMap(num => List(num))
    println("======================")
    newRdd1.collect().foreach(println)
    println("======================")

    val list2 = List("Hello World")
    val rdd2 = sc.makeRDD(list2)
    rdd2.flatMap(s=>s).collect().foreach(println)
    rdd2.flatMap(s=>s.split(" ")).collect().foreach(println)


    sc.stop()

  }

}
