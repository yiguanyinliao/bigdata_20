package rdd

import org.apache.spark.{SparkConf, SparkContext}

object Spark43_RDD_Operate_Transform28 {

  def main(args: Array[String]): Unit = {


    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)

    // TODO Spark  - 转换算子 - Key-Value类型
    //  join: 两个RDD，相同的key会将value连接在一起


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

    val joinRDD = rdd.join(rdd1)
    joinRDD.collect().foreach(println)


    // TODO join 存在笛卡尔乘机，中间处理的数据会非常的大，影响效率
    //  join 内连接。
    val joinRDD1 = rdd2.join(rdd3)
    joinRDD1.collect().foreach(println)
    println("=========================")

    // TODO 左外连接
    rdd1.leftOuterJoin(rdd2).collect().foreach(println)
    println("=========================")
    // TODO 右外连接
    rdd1.rightOuterJoin(rdd2).collect().foreach(println)


    sc.stop()


  }


}
