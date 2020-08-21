package rdd

import org.apache.spark.{SparkConf, SparkContext}

object Spark39_RDD_Operate_Transform24 {

  def main(args: Array[String]): Unit = {


    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)

    // TODO Spark  - 转换算子 - Key-Value类型

//    val list = List(1,2,3,4)
    val list = List(
      ("a",1),("a",2),("c",3),
      ("b",4),("c",5),("c",6),
    )
    val rdd = sc.makeRDD(list,2)


    val newRDD1 = rdd.reduceByKey(_+_)
    newRDD1.collect().foreach(println)

    // TODO 如果分区内计算规则和分区间计算规则相同的场合，类似于reduceByKey
    val newRDD = rdd.aggregateByKey(0)(
      (x, y) => x + y,
      (x, y) => x + y
    )
    newRDD.collect().foreach(println)


    //  TODO 如果分区内计算规则和分区间计算规则相同的场合，Spark提供了一个简洁的场合
    //   foldByKey  计算WordCount.
    val newRDD2 = rdd.foldByKey(0)(_+_)
    newRDD2.collect().foreach(println)

    // TODO 可以计算WordCount的算子
    //  1.groupBy
    //  2.groupByKey
    //  3.reduceByKey
    //  4.aggregateByKey
    //  5.foldByKey



    sc.stop()


  }

}
