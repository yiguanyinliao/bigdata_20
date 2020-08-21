package chapter07

import scala.collection.mutable.ListBuffer

object Scala13_Collection_seq4 {

  def main(args: Array[String]): Unit = {

    // TODO Scala 集合 - seq 可变序列

    val buffer = ListBuffer("a","b","c","d")

    // 产生新的集合
    val buffer1: ListBuffer[Any] = buffer:+1

    // 不产生新的集合
    val buffer2: ListBuffer[String] = buffer += "d"

    // += 方法要求传递的采纳数和当前集合的类型保持一致
//    val buffer3: ListBuffer[String] = buffer += 1 {X}

    // 不产生新的list
    val buffer4: ListBuffer[String] = buffer -= "a"

    // 产生新的list
//    val buffer5: ListBuffer[String] = buffer - "a"


    println(buffer eq buffer1)
    println(buffer eq buffer2)
    println(buffer)
    println(buffer4)
    println(buffer4 eq buffer)
  }

}
