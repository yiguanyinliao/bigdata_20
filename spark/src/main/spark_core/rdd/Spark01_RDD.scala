package rdd

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark01_RDD {

  def main(args: Array[String]): Unit = {

    // TODO RDD的创建
    //  1、创建方式
    //    1.1 内存中创建：List(1,2,3,4)
    //    1.2 存储中创建:File
    //    1.3 从RDD创建
    //    1.4 直接new

    // TODO RDD 之间的运算体现了装饰者模式，会随着RDD的增加增加其功能并作为一个整体来实现

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("spark")
    val sc = new SparkContext(sparkConf)

    val list = List(1,2,3,4)
    // TODO  从集合中创建RDD
    // 使用parallelize方法将集合转换为RDD，进行操作
    // parallelize：并行处理集合中的数据
    // Scala集合：List.par

    val numRDD: RDD[Int] = sc.parallelize(list)
    numRDD.collect().foreach(println)

    // makeRDD: 生成RDD对象
    // 底层其实用的就是parallelize方法，所以逻辑上他们没有区别.
    val numRDD1: RDD[Int] = sc.makeRDD(list)  // 见名知意
    numRDD1.collect().foreach(println)


  }

}
