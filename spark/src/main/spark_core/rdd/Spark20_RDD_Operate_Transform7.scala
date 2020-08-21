package rdd

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark20_RDD_Operate_Transform7 {

  def main(args: Array[String]): Unit = {


    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)

    // TODO Glom   把一个分区当做一个数组来使用

    val list = List(1, 2, 3, 4, 5, 6, 7)
    val rdd = sc.makeRDD(list, 3)
    rdd.saveAsTextFile("ooo")
    val rdd1: RDD[Array[Int]] = rdd.glom()
    rdd1.collect().foreach(
      array => println(array.mkString(", "))
    )
    // TODO   小练习：取每个分区的最大值然后求和.

    val rdd2 = rdd.glom()
    rdd2.collect().foreach(
      array => println(array.mkString(", "))
    )
    val rdd3: RDD[Int] = rdd2.map(
      array => {
        array.max
      }

    )
    val ints: Array[Int] = rdd3.collect()
    println(ints.sum)

    sc.stop()

  }

}
