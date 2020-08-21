package rdd

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark38_RDD_Operate_Transform23 {

  def main(args: Array[String]): Unit = {


    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)

    // TODO Spark  - 转换算子 - Key-Value类型
    //  取出每个分区内相同key的最大值然后分区间相加.

//    val list = List(1,2,3,4)
    val list = List(
      ("a",1),("a",2),("c",3),
      ("b",4),("c",5),("c",6),
    )
    val rdd = sc.makeRDD(list,2)
    // 0分区 => (a,2)(c,3)
    // 0分区 => (b,4)(c,6)
    // mergy => (a,2)(c,9)(b,4).
//    println(rdd.mapPartitions(
//      iter => List(iter.max).iterator
//    ).collect().sum)
//    val value: RDD[(String, Iterable[(String, Int)])] = rdd.groupBy(_._1) //(X)
//    val value: RDD[(String, Iterable[Int])] = rdd.groupByKey() // (X)
    //    reduceByKey分区内和分区间计算规则相同。


    // TODO aggregateByKey: 根据Key聚合数据，使用函数柯里化
    //  第一个参数列表中传递的参数为：zeroValue（初始值）
    //    当分区内一个key出现时，无法执行分区内的两两计算规则
    //    所以需要准备一个初始值和第一个值进行分区内的计算
    //  第二个参数列表中传递2个参数：
    //    seqOp:分区内计算规则，相同key的数据在分区内两两计算的规则
    //    combOp:分区间计算规则，相同key的数据在分区间两两计算的规则


//    val newRDD = rdd.aggregateByKey(0)( // 初始值不一样，最终结果不一样
    val newRDD = rdd.aggregateByKey(5)(
      (x, y) => math.max(x, y),
      (x, y) => x + y
    )
    newRDD.collect().foreach(println)

    sc.stop()


  }

}
