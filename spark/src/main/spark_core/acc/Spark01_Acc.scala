package acc

import org.apache.spark.{SparkConf, SparkContext}

object Spark01_Acc {

  def main(args: Array[String]): Unit = {

    // TODO Spark - 累加器
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)

    val rdd = sc.makeRDD(
      List(1, 2, 3, 4),2
    )

//    rdd.reduce(_+_)

    //TODO RDD 执行的效率和Shuffle有关系，当数据量很大，Shuffle会变慢，性能会下降
    // 一般提升Spark的性能就是提升Shuffle的性能
    // 1.数据量减少（Filter把无用数据过滤掉）
    // 2.预聚合
    // 3.Shuffle本身的IO。

    // 不使用Shuffle

    // TODO 算子：所谓的算子，其实就是RDD的方法
    //  map:RDD方法外和方法内部的代码执行位置不一样
    //  Scala集合的方法不称为算子
    //    map：内存中处理数据，不涉及到分布式计算的操作

    // TODO sum变量由于闭包的原因，会随着数据和任务发给Executor进行计算
    //  但是计算完成后，sum变量不会返回到Driver端，Driver端的sum没有被更新
    //  所以最终的打印是0。

    var sum = 0  // Driver端
//    rdd.collect().foreach(num=>{
    rdd.foreach(num=>{  // 分布式遍历，有两个分区
      sum = sum + num  // Executor端
      println("sum=" + sum)   // 结果：1、3、3、7

    })
    println("sum=" + sum)   // 在Executor端的执行结果没有回传到Driver端

    sc.stop()

  }

}
