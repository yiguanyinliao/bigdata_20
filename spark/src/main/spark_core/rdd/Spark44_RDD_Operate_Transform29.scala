package rdd

import org.apache.spark.{SparkConf, SparkContext}

object Spark44_RDD_Operate_Transform29 {

  def main(args: Array[String]): Unit = {


    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)

    // TODO Spark  - 转换算子 - Key-Value类型
    //  cogroup: co => connect,   group + connect


    val list = List(
      ("a", 1), ("c", 2), ("b", 3)
    )
//
//    val list1 = List(
//      ("a", 4), ("c", 5), ("b", 6)
//    )

    val list1 = List(
       ("c", 5), ("b", 6),("a", 4)     // 根据相同的可以做拼接
    )

    val list2 = List(
      ("a", 1), ("b", 3)
    )

    val list3 = List(
      ("a", 5), ("b", 6),("a", 4)
    )

    val rdd = sc.makeRDD(list)
    val rdd1 = sc.makeRDD(list1)
    val rdd2 = sc.makeRDD(list2)
    val rdd3 = sc.makeRDD(list3)

    rdd1.cogroup(rdd2).collect().foreach(println)
    println("===========================")
    rdd.cogroup(rdd3).collect().foreach(println)
    println("===========================")
    rdd3.cogroup(rdd).collect().foreach(println)

    sc.stop()


  }


}
