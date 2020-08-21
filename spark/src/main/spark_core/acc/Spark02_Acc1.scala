package acc

import org.apache.spark.{SparkConf, SparkContext}

object Spark02_Acc1 {

  def main(args: Array[String]): Unit = {

    // TODO Spark - 累加器
    //  累加器用来把Executor端变量信息聚合到Driver端
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)

    val rdd = sc.makeRDD(
      List(1, 2, 3, 4),2
    )

    // TODO 累加器（分布式累加只写变量）：只写的概念是多个Executor节点无法互相访问累加器的值，只能更新（写）
    //  当计算完毕后，只有Driver端可以访问每一个节点的计算结果
    //  就是是Spark计算的时候，变量从Driver端传递到Executor端进行计算
    //  计算完毕后，Spark会将每一个Executor的计算结果返回到Driver端进行聚合


    // TODO 使用累加器对数据进行操作
    //  创建累加器
    val sum = sc.longAccumulator(name = "sum")
//    sc.doubleAccumulator
//    sc.collectionAccumulator

    rdd.foreach(num=>{
//      sum = sum + num
      sum.add(num)   // TODO 使用累加器

    })
    println("sum=" + sum.value) // TODO 获取累加器的值

    sc.stop()

  }

}
