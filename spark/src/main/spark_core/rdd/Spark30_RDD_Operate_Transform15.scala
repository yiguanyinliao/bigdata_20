package rdd

import org.apache.spark.{SparkConf, SparkContext}

object Spark30_RDD_Operate_Transform15 {

  def main(args: Array[String]): Unit = {


    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)

    // TODO Spark  - 转换算子 -coalesce（缩减分区,合并分区）
    //  将多个分区减少分区，提高效率。

    val list = List(1,2,3,4,5,6,7,8,9,10)
//    val rdd = sc.makeRDD(list,10)
    val rdd = sc.makeRDD(list,4)

    // TODO coalesce 算子一般就用于缩减分区，而不是扩大分区
    //  Spark提供了另外一个算子用于扩大分区：repartition
    //  repartition 其实底层就是使用shuffle的coalesce算子
    //  扩大分区一定要将数据打乱重新作何

    // TODO  coalesce缩减分区时，默认不使用shuffle，想要均衡数据时，可以使用shuffle。
    val newRdd = rdd.coalesce(2)
//    val newRdd1 = rdd.coalesce(6)
    val newRdd1 = rdd.coalesce(6,true)
    val newRdd2 = rdd.repartition(6)   // 扩大分区一般用repartition
    rdd.saveAsTextFile("output1")
    newRdd.saveAsTextFile("output2")
    newRdd1.saveAsTextFile("output3")

    sc.stop()

  }

}
