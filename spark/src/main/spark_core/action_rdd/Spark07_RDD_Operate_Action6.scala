package action_rdd

import org.apache.spark.{SparkConf, SparkContext}

object Spark07_RDD_Operate_Action6 {

  def main(args: Array[String]): Unit = {


    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)

    // TODO Spark - 行动算子 -  foreach

//    val list = List(1,2,3,4)
    val list = List(3,4,1,2)
    val rdd = sc.makeRDD(list,2)


    val ints: Array[Int] = rdd.collect()
    ints.foreach(println)

    rdd.collect().foreach(println)
    println("=====================")

    // TODO 所谓的算子就是RDD的方法，但是由于存在分布式的计算能力，为了和scala集合的方法分开，
    //  rdd对象的方法称为算子
    //  Driver（）<=> Executor(计算）
    //  算子外部的代码执行位置是Driver端
    //  算子内部的代码执行位置是Executor端
    rdd.foreach(println)      // 分布式打印，顺序不是有序的

    // TODO 分布式遍历,分区间顺序不确定
    //  执行遍历时，会将你们函数作为任务的一部分传递给Executor执行

    sc.stop()


  }


}
