package rdd

import org.apache.spark.{SparkConf, SparkContext}

object Spark32_RDD_Operate_Transform17 {

  def main(args: Array[String]): Unit = {


    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)

    // TODO Spark  - 转换算子 - 双value
    //  所谓的双value就是两个RDD的操作
    //  交集、并集、差集、拉链。

    val list1 = List(1,2,3,4)
    val list2 = List(3,4,5,6)
//    val list2 = List(3,4,5,6,7,8)
//    val list2 = List(3,4,5,6,7)
    val list3 = List("3","4","5","6","7")
    val list4 = List("3","4","5","6")

    val rdd1 = sc.makeRDD(list1,2)
    val rdd2 = sc.makeRDD(list2,2)
    val rdd3 = sc.makeRDD(list3,2)
    val rdd4 = sc.makeRDD(list4,2)

    // 并集
    // TODO 如果类型不匹配，两个RDD无法形成并集
    println(rdd1.union(rdd2).collect().mkString(","))
//    println(rdd1.union(rdd3).collect().mkString(","))   // 类型不一致（X）

    // 交集
    // TODO 如果类型不匹配，两个RDD无法形成交集
    println(rdd1.intersection(rdd2).collect().mkString(","))
//    println(rdd1.intersection(rdd3).collect().mkString(","))   // 类型不一致（X）

    // 差集
    // TODO 如果类型不匹配，两个RDD无法形成差集
    println(rdd1.subtract(rdd2).collect().mkString(","))
//    println(rdd1.subtract(rdd3).collect().mkString(","))   // 类型不一致（X）

    // TODO 拉链（zip）
    //  执行拉链的两个RDD每个分区的元素数量必须一致，不一致会报错
    //  只有相同分区数量、每个分区数量相同的两个RDD才会进行操作
    //  Can only zip RDDs with same number of elements in each partition
    //  Can't zip RDDs with unequal numbers of partitions
    //  分区类型不一致可以拉链。
    println(rdd1.zip(rdd2).collect().mkString(","))
    println(rdd1.zip(rdd4).collect().mkString(","))

    sc.stop()

  }

}
