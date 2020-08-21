package acc

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}


object Spark04_Bc {

  def main(args: Array[String]): Unit = {

    // TODO Spark - 广播变量
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)

    val rdd1 = sc.makeRDD(List(("a", 1), ("b", 2), ("c", 3)))
    val rdd2 = sc.makeRDD(List(("a", 4), ("b", 5), ("c", 6)))

    val testmap = Map(("a", 4), ("b", 5), ("c", 6))

    val joinRDD: RDD[(String, (Int, Int))] = rdd1.join(rdd2)

    // TODO join 会产生笛卡尔乘机，数据量有可能非常大
    //  数据有可能被打乱重新组合，中间可能会有Shuffle。
    println(joinRDD.collect().mkString(","))

    // TODO 有没有可能不使用Shuffle ？

    rdd1.map {
      case (k, v) => {
        var v1 = testmap.getOrElse(k,0)
        (k, (v, v1))
      }
    }.collect().foreach(println)


    sc.stop()

    // TODO Spark 可以将多个Task发给一个Executor
    //  具体任务的执行情况取决于CPU核数
    //  任务传递时，如果任务存在闭包变量，那么这个变量会和task进行关联一块传递
    //  这样如果一个Executor存在多个task，有可能造成变量的冗余，一个Executor中存在多个变量副本
    //  导致消耗内存空间，影响执行效率
    //  那么如果把闭包变量保存到Executor的内存中共享数据，所有Executor的任务都可以使用
    //  广播变量可以执行这个操作。

  }

}
