package rdd

import org.apache.spark.{SparkConf, SparkContext}

object Spark29_RDD_Operate_Transform14 {

  def main(args: Array[String]): Unit = {


    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)

    // TODO Spark  - 转换算子 -coalesce（缩减分区,合并分区）
    //  将多个分区减少分区，提高效率。

    val list = List(1,2,3,4,5,6,7,8,9,10)
//    val rdd = sc.makeRDD(list,10)
    val rdd = sc.makeRDD(list,4)

    // TODO 缩减分区
    //  1.合并分区时，会不会将数据打乱重新组合，是否存在shuffle操作？
    //    默认不存在shuffle过程，因为没有将数据打乱，只是分区间组合
    //    如果合并后，可能会产生 数据倾斜 问题，为了避免这个问题，可以采用shuffle.
    //  2.如果缩减分区的数值大于原来的分区数量，会出现什么情况？
    //    如果不采用shuffle，参数大于原分区数，分区合并后分区数不变
    //    如果采用shuffle，会打乱根据参数数值设置分区。

    // TODO coalesce算子的第二个参数表示是否采用shuffle操作，默认为false
    val newRdd = rdd.coalesce(2)
//    val newRdd1 = rdd.coalesce(6)
    val newRdd1 = rdd.coalesce(6,true)
    rdd.saveAsTextFile("output1")
    newRdd.saveAsTextFile("output2")
    newRdd1.saveAsTextFile("output3")

    sc.stop()

  }

}
