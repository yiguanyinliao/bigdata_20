package serial

import org.apache.spark.{SparkConf, SparkContext}

object Spark02_Ser1 {

  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)

    // TODO Spark - 序列化

//    val list = List()   // 不继承Serializable 也会报错
    val list = List(1, 2, 3, 4)
    val rdd = sc.makeRDD(list)

    // Driver端
    val user = new User()

    // Task not serializable
    // java.io.NotSerializableException: serial.Spark02_Ser1$User
    // 如果Executor端使用了Driver端的数据，这个数据需要序列化，否则无法在网络专供传递
    // 闭包检测是执行作业的前提条件，如果检测失败，那么作业无法执行
    rdd.foreach(
      num => {
        // Executor端
        println(user)
      }
      )


    sc.stop()

  }

  class User extends Serializable {
//  class User{

  }

}
