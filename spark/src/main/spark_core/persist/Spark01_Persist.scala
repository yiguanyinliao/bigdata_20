package persist

import org.apache.spark.{SparkConf, SparkContext}

object Spark01_Persist {

  def main(args: Array[String]): Unit = {

    // TODO Spark - 持久化
    //  所谓的持久化，表示将内存的数据保存到磁盘文件中
    //  Spark在执行作业时，由于RDD血缘关系的存在，一旦出现错误，计算就需要从头开始计算
    //  这种操作体现了框架处理的容错性，但性能会受到极大的影响
    //  1.希望保证容错性的基础上可以提高性能?
    //    如果增加缓存操作
    //  2.重复计算时可以提高性能?
    //    如果增加缓存操作。


    // TODO 如果使用缓存将RDD的计算结果进行保存，即使RDD中不保存数据，那么缓存中有数据
    //  就可以重复使用提高效率，一旦出现错误，就可以从缓存中获取中间出了的数据，也可以继续执行。
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)

    val rdd = sc.makeRDD(List(1,2,3,4))

    val mapRDD = rdd.map(
      num => {
        println("map......")
        num
      }
    )

    //TODO 将RDD的计算结果缓存起来，重复使用
    // Spark中缓存处理也叫做持久化，级别为 MEMORY_ONLY
    // 正常情况下将一些重要的数据、执行时间比较长、重复率比较高的数据进行缓存
    // Spark中持久化有存储级别的概念，cache就是存在内存中，想要保存到其他级别，需要使用persist()
    // cache可以改变血缘关系，当执行出现错误的情况下，可以根据血缘关系从缓存中共获取数据
    // 一般情况下，将数据保存到cache中.但是想要长久的一般保存到分布式存储中HDFS
    mapRDD.cache()
//    mapRDD.persist(StorageLevel.DISK_ONLY)

    println(mapRDD.toDebugString)
    println(mapRDD.collect().mkString(","))
    println("*******************************")
    println(mapRDD.toDebugString)

    // TODO 1.全数据操作
    println(mapRDD.collect().mkString(","))
    println("*******************************")
    // TODO 2.jishu1caozuo1
//    mapRDD.filter(num => num % 2 == 1)
    println(mapRDD.filter(_ % 2 != 0).collect().mkString(","))


    sc.stop()

  }

}
