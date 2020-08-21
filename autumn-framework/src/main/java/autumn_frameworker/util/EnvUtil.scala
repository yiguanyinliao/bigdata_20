package autumn_frameworker.util

import org.apache.spark.SparkContext

object EnvUtil {

  private val scLocal: ThreadLocal[SparkContext] = new ThreadLocal[SparkContext]()

  def getEnv():SparkContext={
    scLocal.get()
  }

  def setEnv(sc:SparkContext): Unit ={
    scLocal.set(sc)
  }

}
