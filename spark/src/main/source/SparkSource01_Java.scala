import java.util

import scala.collection.mutable.ArrayBuffer

object SparkSource01_Java {

  def main(args: Array[String]): Unit = {

    // Java 中集合和scala集合转换。

    val javaList = new util.ArrayList[String]()
    javaList.add("zhangsan")
    javaList.add("lisi")
    javaList.add("wangwu")
    println(javaList)

    val scalaBuff = ArrayBuffer(1,2,3,4)
    println(scalaBuff)

    // Java 集合和Scala集合需要转换，需要导入隐式转换规则
    import scala.collection.JavaConverters._
    javaList.asScala.foreach(println)  // Java 转 Scala
    println("============================")
//    javaList.forEach()
    scalaBuff.foreach(println)
    println(scalaBuff.asJava) // Scala 转 Java

  }

}
