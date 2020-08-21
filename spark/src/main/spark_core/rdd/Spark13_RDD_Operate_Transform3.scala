package rdd

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark13_RDD_Operate_Transform3 {

  def main(args: Array[String]): Unit = {


    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)
    // TODO 小练习：从服务器日志数据apache.log中获取用户请求URL资源路径

    val rdd: RDD[String] = sc.textFile("input/apache.log")
    val newRdd = rdd.map(
      line => {
        val datas: Array[String] = line.split(" ")
        datas(6)
      }
    )
    newRdd.collect().foreach(println)


    sc.stop()

  }

}
