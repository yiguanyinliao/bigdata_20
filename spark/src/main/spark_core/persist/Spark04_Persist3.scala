package persist

import org.apache.spark.{SparkConf, SparkContext}

object Spark04_Persist3 {

  def main(args: Array[String]): Unit = {

    // TODO Spark - 持久化

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)
    // 设置检查点的保存路径，在工作中一般为分布式存储路径
    sc.setCheckpointDir("cp")

    val rdd = sc.makeRDD(List(1,2,3,4),2)

    val mapRDD1 = rdd.map(
      num => {
        println("map......")
        num
      }
    )
    val mapRDD2 = rdd.map(
      num => {
        println("map......")
        num
      }
    )
    val mapRDD3 = rdd.map(
      num => {
        println("map......")
        num
      }
    )

    // cache 方法会在血缘关系里增加依赖
    //      cache会缓存数据，但是数据不安全，可能会丢失数据
    //      为了避免数据统计结果有问题，所以需要重新计算，所以血缘关系不会丢失
    // checkpoint方法会切断血缘关系
    //      在生产环境中，检查点的数据保存在分布式存储中，数据会安全，所以不需要考虑数据丢失的问题
    //      可以将检查点的数据当成数据源，所以可以重写计算血缘关系
    mapRDD3.checkpoint()  // Job

    println(mapRDD3.toDebugString)
    println("==============================")
    mapRDD3.collect().mkString(",")
    println(mapRDD3.toDebugString)


    sc.stop()

  }

}
