package action_rdd

import org.apache.spark.{SparkConf, SparkContext}

object Spark01_RDD_Operate_Action {

  def main(args: Array[String]): Unit = {


    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)

    // TODO Spark - 行动算子
    //  所谓的行动算子，其实是通过执行对应的方法将作业运行起来
    //  collect 算子其实就是行动算子
    //  行动算子调用一次，作业就会执行一次
    //  所有的行动算子的核心功能其实是runjob
    val list = List(1,2,3,4)
    val rdd = sc.makeRDD(list)

    // TODO 转换算子会产生新的RDD
    val newRDD = rdd.map(_*2)
    rdd.collect().foreach(println)
    println("========================")
    rdd.collect().foreach(println)

    sc.stop()


  }


}
