package rdd

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark03_RDD_Par {

  def main(args: Array[String]): Unit = {


//    val sparkConf = new SparkConf().setMaster("local[2]").setAppName("spark")
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("spark")
    val sc = new SparkContext(sparkConf)
    // TODO RDD的分区和并行度
    //  RDD的分区主要用于分布式计算，可以将数据发送到不同的Executor进行计算，和并行是有关系的
    //  并行度表示在整个集群执行时，同时执行任务的数量
    //  分区的数量和并行度没有直接的关系，主要取决于CPU核数
    //  RDD的分区数量可以在创建时更改，如果不更改，就是有默认分区。

    val list = List(1,2,3,4)
    // makeRDD的第二个参数就是默认分区的数量
    // 默认是当前Spark环境的总核数。
    val number: RDD[Int] = sc.makeRDD(list,2)
    // 讲RDD保存成分区文件
    number.saveAsTextFile("output")


    sc.stop()

  }

}
