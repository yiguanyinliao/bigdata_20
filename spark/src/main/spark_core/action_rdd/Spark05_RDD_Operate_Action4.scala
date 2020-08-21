package action_rdd

import org.apache.spark.{SparkConf, SparkContext}

object Spark05_RDD_Operate_Action4 {

  def main(args: Array[String]): Unit = {


    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)

    // TODO Spark - 行动算子 -  fold
    //  aggregate分区内和分区间操作一致时的简化版.


    val list = List(1,2,3,4)
    val list1 = List("a","b","a","b")
    val rdd = sc.makeRDD(list,2)
    val rdd1 = sc.makeRDD(list1,2)

    val sum = rdd.fold(0)(_+_)
    val sum1 = rdd.fold(10)(_+_)
    println(sum)
    println(sum1)

    // TODO Spark - 行动算子 -  countByKey
    //  计算WordCount。
    val intToLong: collection.Map[Int, Long] = rdd.map((_,1)).countByKey()
    val stringToLong: collection.Map[String, Long] = rdd1.map((_,1)).countByKey()
    println(intToLong)
    println(stringToLong)

    // TODO Spark - 行动算子 -  countByValue
    //  计算wordcount
    //  value类型的RDD，双value类型的RDD，KV类型的RDD。
    val intToLongValue: collection.Map[Int, Long] = rdd.countByValue()
    val value= rdd.map((_,1)).countByValue()
    val stringToLong1: collection.Map[String, Long] = rdd1.countByValue()
    println(intToLongValue)
    println(value)
    println(stringToLong1)



    sc.stop()


  }


}
