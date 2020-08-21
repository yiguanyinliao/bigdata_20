package rdd

import org.apache.spark.rdd.RDD
import org.apache.spark.{HashPartitioner, Partitioner, SparkConf, SparkContext}

object Spark34_RDD_Operate_Transform19 {

  def main(args: Array[String]): Unit = {


    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)

    // TODO Spark  - 转换算子 - Key-Value类型

    val list = List(
      ("NBA","xxxxxx"),
      ("CBA","xxxxxx"),
      ("WNBA","xxxxxx"),
      ("NBA","yyyyy")
    )

    val rdd = sc.makeRDD(list,2)
    // TODO 如果重分区器和当前RDD的分区器一样，会不进行任何处理，不会再次重分区

    val newRdd: RDD[(String, String)] = rdd.partitionBy(new MyPartitioner())
    newRdd.saveAsTextFile("output")

    sc.stop()

  }

  // TODO 自定义分区器
  class MyPartitioner extends Partitioner{
    override def numPartitions: Int = 3

    override def getPartition(key: Any): Int = {
      // 根据数据的key决定分区号

      key match {
        case "NBA" => 0
        case "CBA" => 1
        case "WNBA" => 2
      }
    }
  }

}
