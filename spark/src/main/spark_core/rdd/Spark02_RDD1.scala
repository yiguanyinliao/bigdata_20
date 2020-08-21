package rdd

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark02_RDD1 {

  def main(args: Array[String]): Unit = {

    // TODO RDD的创建
    //  1、创建方式
    //    1.1 内存中创建：List(1,2,3,4)
    //    1.2 存储中创建:File
    //    1.3 从RDD创建
    //    1.4 直接new

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("spark")
    val sc = new SparkContext(sparkConf)

    // TODO  从外部存储中创建RDD
    //  所谓的存储系统,基本上是文件系统、数据库、Hbase
    //  path 表示文件的路径
    //  Spark通过textFile一行一行读取文件
    //  * 号可以在文件路径中代表通配符
    //  path路径可以是具体的文件，也可以是目录.

//    val file: RDD[String] = sc.textFile("input/word.txt")
//    val file: RDD[String] = sc.textFile("input/word*.txt")
//    val file: RDD[String] = sc.textFile("input/*.txt")
//    val file: RDD[String] = sc.textFile("input")
//    val file: RDD[String] = sc.textFile("input/word.txt,input/word1.txt")  // 中间不能有空格
    val file: RDD[String] = sc.textFile("{input/word.txt},{input/word1.txt}")
    file.collect().foreach(println)
    sc.stop()


  }

}
