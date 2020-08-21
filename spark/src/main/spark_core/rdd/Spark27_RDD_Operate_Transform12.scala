package rdd

import org.apache.spark.{SparkConf, SparkContext}

object Spark27_RDD_Operate_Transform12 {

  def main(args: Array[String]): Unit = {


    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)

    // TODO Spark  - 转换算子 -distinct（去重）

    val list = List(1,2,3,4,1,2,3,4)
    val rdd = sc.makeRDD(list,2)
    // (1,1)          // 底层原理
    // => (1,null),(1,null)
    // 1,(null,null) => (1,null)
    // => (1).
    // map(x => (x, null)).reduceByKey((x, _) => x, numPartitions).map(_._1)
//    val newRdd = rdd.distinct(3)
    val newRdd = rdd.distinct()
    newRdd.collect().foreach(println)

    rdd.saveAsTextFile("output")
    // distinct 可以将数据打乱重新组合，存在shuffle过程
    rdd.distinct().saveAsTextFile("output1")
    // 如果数据存在shuffle过程，那么可以改变分区
    rdd.distinct(3).saveAsTextFile("output2")


    sc.stop()

  }

}
