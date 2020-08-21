package rdd

import org.apache.spark.{SparkConf, SparkContext}

object Spark09_RDD_Par6 {

  def main(args: Array[String]): Unit = {


    //    val sparkConf = new SparkConf().setMaster("local[2]").setAppName("RDD")
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)

    /*
        totalSize:17
        num:3
        goalSize: 17/3 = 5 余 2>10%

        partion cnt = num + 1 => 4

        1@@ => 0,1,2
        23@@ => 3,4,5,6
        456@@ => 7,8,9,10,11
        78910@@ => 12,13,14,15,16,17


        // 0 - 5      => [0,5]     => 【1，23】
        // 5 - 10     => [5,10]    => 【456】
        // 10 - 15    => [10,15]   => 【78910】
        // 15 - 17    => [15,17]   => 【】
     */

    val rdd1 = sc.textFile("input/num1.txt",3)
//    val rdd2 = sc.textFile("input/num1.txt",4)
    rdd1.saveAsTextFile("output1")
//    rdd2.saveAsTextFile("output2")

    sc.stop()

  }

}
