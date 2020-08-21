package rdd

import org.apache.spark.rdd.RDD
import org.apache.spark.{HashPartitioner, SparkConf, SparkContext}

object Spark33_RDD_Operate_Transform18 {

  def main(args: Array[String]): Unit = {


    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)

    // TODO Spark  - 转换算子 - Key-Value类型

    val list = List(1,2,3,4)
    val rdd: RDD[Int] = sc.makeRDD(list,2)
    val rdd1: RDD[(Int, Int)] = sc.makeRDD(list,2).map((_,1))

    //TODO RDD中默认没有partition方法，所以无法调用
    // Scala编译器可以在编译出现错误的场合下，尝试进行规则查找，找到可以让出现编译通过的的规则（隐式转换规则）
    // Spark提供了 隐式转换 的能力：RDD => PairRDDFunctions
    // PairRDDFunctions类中提供了很多面向KV类型数据的处理方法

    // TODO partitionBy 根据指定的规则对数据进行了重分区
    //  reparation 表示分区的变化
    //  partitionBy 更强调数据重新分区
    //  partitionBy 传递的参数是分区器：partitioner
    //    Spark默认提供了3个分区器，主要是2个：Hash，Range
    //    Spark默认采用Hash分区器
    //  partitionBy 存在shuffle操作
    //  Rang分区器会在排序是使用，自己使用的不多

    val rdd2: RDD[(Int, Int)] = rdd1.partitionBy(new HashPartitioner(2))
    rdd1.saveAsTextFile("output1")
    rdd2.saveAsTextFile("output2")



  }

}
