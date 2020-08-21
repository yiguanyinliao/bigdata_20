package depence

import org.apache.spark.{SparkConf, SparkContext}

object Spark01_Dep {

  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)

    // TODO 在当前RDD中会保留之前RDD的所以血缘关系
    //  RDD 的toDebugString方法可以查看当前RDD的 全部血缘关系 ，不是依赖关系
    //  RDD 的dependencies方法可以查看 两个相邻RDD 的依赖关系。
    val list = List(1,2,3,4)
    val rdd = sc.makeRDD(list,2)

//    println(rdd.toDebugString)
    println(rdd.dependencies)
    println("===========================")
    val mapRDD = rdd.map((_,1))
//    println(mapRDD.toDebugString)
    println(mapRDD.dependencies)
    println("===========================")
    val reduceRDD = mapRDD.reduceByKey(_+_)
//    println(reduceRDD.toDebugString)
    println(reduceRDD.dependencies)
    println("===========================")
    reduceRDD.collect().foreach(println)

    sc.stop()

  }



}
