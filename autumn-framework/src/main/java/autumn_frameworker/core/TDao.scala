package autumn_frameworker.core

import autumn_frameworker.util.EnvUtil
import org.apache.spark.rdd.RDD


/**
  * 数据访问特质
  */
trait TDao {
  /**
    * 从集合中获取数据
    */
  def fromCollection(): Unit ={

  }

  /**
    * 从文件中获取数据
    */

  def fromFile(path:String): RDD[String] ={
    EnvUtil.getEnv().textFile(path)
  }

}
