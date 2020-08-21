package chapter07

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

object Scala14_Collection_seq5 {

  def main(args: Array[String]): Unit = {

    // TODO Scala 集合 - seq 可变序列 和 不可变序列的转换

    val list = List(1,2,3,4)
    val buffer: ListBuffer[String] = ListBuffer("a", "b", "c", "d")


    // 不可变 => 可变
    val buffer1: mutable.Buffer[Int] = list.toBuffer
    println(buffer1)

    // 可变 => 不可变
    val list1: List[String] = buffer.toList
    println(list1)
  }

}
