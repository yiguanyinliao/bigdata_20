package rdd

import org.apache.spark.{SparkConf, SparkContext}

object Spark16_RDD_test {

  def main(args: Array[String]): Unit = {


    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)

    // TODO 小练习：获取第二个数据分区的数据

    val list = List(1,2,3,4,5,6)
    val rdd = sc.makeRDD(list,3)
    val newRdd = rdd.mapPartitionsWithIndex(
      (index, iter) => {
        if (index == 1) {
          iter
        }
        else {
//          null  (X)
          Nil.iterator           // 一次性的
//          List().iterator
//          iter.filter(x=>false)    // 需要每次都迭代
        }
      }
    )
    newRdd.collect().foreach(println)

    sc.stop()

  }

}
