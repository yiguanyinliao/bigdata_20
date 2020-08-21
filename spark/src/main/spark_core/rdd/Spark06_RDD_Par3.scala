package rdd

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark06_RDD_Par3 {

  def main(args: Array[String]): Unit = {


//    val sparkConf = new SparkConf().setMaster("local[2]").setAppName("spark")
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("spark")
    val sc = new SparkContext(sparkConf)


    // TODO 在加载数据时，可以设定并行度来设置分区数量
    val list = List(1,2,3,4)
    // 使用makeRDD时，不传递第二个参数，会默认配置默认的并行度
    val rdd1 = sc.makeRDD(list)


    // TODO 读取文件时，并行度设置（分区设置）
    //  如果textFile不传递第二个参数，默认使用defaultMinParallelism。
    val rdd2: RDD[String] = sc.textFile("input/word.txt")
    rdd2.saveAsTextFile("output")


    sc.stop()

  }

}
