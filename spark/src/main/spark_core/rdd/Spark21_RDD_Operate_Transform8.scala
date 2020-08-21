package rdd

import org.apache.spark.{SparkConf, SparkContext}

object Spark21_RDD_Operate_Transform8 {

  def main(args: Array[String]): Unit = {


    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)

    // TODO Group by
    var list = List(1,2,3,4)
    val rdd = sc.makeRDD(list)

    // 根据指定的规则对数据进行分组
    // TODO group by 算子会将RDD分区中的数据打乱重新组合
    //  这种操作称为shuffle。
    val newRdd = rdd.groupBy(num => num % 2)
    newRdd.collect().foreach(println)

    sc.stop()

  }

}
