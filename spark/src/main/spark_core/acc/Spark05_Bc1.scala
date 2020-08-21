package acc

import org.apache.spark.broadcast.Broadcast
import org.apache.spark.{SparkConf, SparkContext}


object Spark05_Bc1 {

  def main(args: Array[String]): Unit = {

    // TODO Spark - 广播变量
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)

    val rdd1 = sc.makeRDD(List(("a", 1), ("b", 2), ("c", 3)))

    val testmap = Map(("a", 4), ("b", 5), ("c", 6))

    // TODO 声明广播变量
    val bc: Broadcast[Map[String, Int]] = sc.broadcast(testmap)


    rdd1.map {
      case (k, v) => {
//        var v1 = testmap.getOrElse(k,0)
        var v1 = bc.value.getOrElse(k,0)  // TODO 使用广播变量
        (k, (v, v1))
      }
    }.collect().foreach(println)


    sc.stop()

  }

}
