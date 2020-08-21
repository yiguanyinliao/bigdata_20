package rdd

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark12_RDD_Operate_Transform2 {

  def main(args: Array[String]): Unit = {


    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)

    val list = List(1,2,3,4)
    val rdd: RDD[Int] = sc.makeRDD(list,2)
    // TODO  map算子分区内的每一个数据逻辑全部处理完成才会执行下一条，效率比较低
    //  为了提高数据处理效率，可以将数据一次性发送到Executer来执行
    //  mapPartitions算子可以将一个分区的数据当成一个整体发送到executer执行。

    val newRdd = rdd.map(
      num => {
        println("===========")
        num * 2
      }
    )
    newRdd.collect()    // 打印了4个

    // TODO mapPartitions数据处理的性能比map高
    //  mapPartitions数据处理的规则是：必须保证这个分区的所以数据处理完成，数据才会释放（如果没有全部执
    //  行完，不会释放数据，可能会出现内存溢出问题）。
    val newRdd1: RDD[Int] = rdd.mapPartitions(
      iter => {         // 每个元素是迭代器
        println("**************")  // 打印了2个
        iter.map(_ * 2)
      }
    )
    newRdd1.collect()

    sc.stop()

  }

}
