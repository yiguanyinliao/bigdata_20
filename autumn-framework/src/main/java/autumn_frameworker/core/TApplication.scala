package autumn_frameworker.core

import autumn_frameworker.util.EnvUtil
import org.apache.spark.{SparkConf, SparkContext}

trait TApplication {

  // 控制抽象 ： 将代码逻辑作为参数传递给一个函数
  def execute(op: => Unit): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Spark-WordCount")
    val sc = new SparkContext(sparkConf)

    EnvUtil.setEnv(sc)

    try {
      op
    } catch {
      case ex: Exception => ex.printStackTrace()
    }
    sc.stop()
  }

}
