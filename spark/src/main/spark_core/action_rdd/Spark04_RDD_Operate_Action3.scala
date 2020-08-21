package action_rdd

import org.apache.spark.{SparkConf, SparkContext}

object Spark04_RDD_Operate_Action3 {

  def main(args: Array[String]): Unit = {


    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)

    // TODO Spark - 行动算子 - aggregate
    //  分区的数据通过初始值和分区内的数据进行聚合，然后再和初始值进行分区间的数据聚合.


    val list = List(1,2,3,4)
    val rdd = sc.makeRDD(list,3)

    // TODO aggregate :初始值不仅仅用于分区内的计算，也用于分区间的计算（但是在分区间只会作为初始值出现一次）
    //   aggregateByKey: 初始值用于分区内第一个key对应的value的计算，只在分区内计算

    val i: Int = rdd.aggregate(0)(_+_,_+_)
    val j: Int = rdd.aggregate(10)(_+_,_+_)
    println(i)
    println(j)

    sc.stop()


  }


}
