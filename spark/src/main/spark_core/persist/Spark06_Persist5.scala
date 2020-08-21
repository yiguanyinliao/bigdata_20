package persist

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark06_Persist5 {

  def main(args: Array[String]): Unit = {

    // TODO Spark - 持久化

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)


    val rdd = sc.makeRDD(List("hello","Scala","hello"))

    val mapRDD = rdd.map(
      word => {
        println("map.....")
        (word, 1)
      }
    )

    // TODO cache方法返回的就是当前RDD对象，使用时可以写返回结果，也可以不写，两者没有区别
    val cacheRDD: RDD[(String, Int)] = mapRDD.cache()

    println(cacheRDD.collect().mkString(","))
    println("===============================")
    println(cacheRDD.collect().mkString(","))

//    mapRDD.cache()
//    println(mapRDD.collect().mkString(","))
//    println("===============================")
//    println(mapRDD.collect().mkString(","))
//
    sc.stop()

  }

}
