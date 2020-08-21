package rdd

import org.apache.spark.{SparkConf, SparkContext}

object Spark08_RDD_Par5 {

  def main(args: Array[String]): Unit = {


    //    val sparkConf = new SparkConf().setMaster("local[2]").setAppName("RDD")
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)


    // TODO 在加载数据时，可以设定并行度来设置分区数量
    //  1.分区数量到底是多少？
    // TODO Spark读取文件数据其实是采用的Hadoop文件的读取方式
    //  Spark是不能具体决定分区数量，只能提供一个最小分区数量，具体的分区数量是由Hadoop在读取文件时自动判断的
    //  Hadoop => totalSize(文件总字节总大小)/num（分区数）=> 3/2(X) = 1 => 7/2=> 余1>10% => 会多加一个分区=>3个分区
    //  最终分区数量 = 最小分区数（2） + 可能的分区数量（1）

    //TODO  2.每个分区存储什么数据？
    // Spark不决定数据如何存储，依然是有Hadoop决定的
    // 2.1 Haoop读取文件是按一行一行读取的，不是按照字节的方式，分区的计算和数据读取是不一样的逻辑
    // 2.2 Haoop读取数据是按照数据的偏移量（跟字符没有关系）读取的，偏移量是从0开始的.

    /*
        7/2 =3
        0 => 0+3 => 0-3 => 【1，2】  // 按行读取会把2的一行也全部读取过来
        1 => 3+3 => 3-6 => 【3】
        2 => 6+1 => 6-7 => 【】

        数据：
        1@@   => 0,1,2
        2@@   => 3,4,5
        3     => 6

     */

    val rdd1 = sc.textFile("input/num.txt",2)
    rdd1.saveAsTextFile("output1")

    sc.stop()

  }

}
