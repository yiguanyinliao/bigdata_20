package chapter07

import scala.collection.mutable.ListBuffer

object Scala12_Collection_seq3 {

  def main(args: Array[String]): Unit = {

    // TODO Scala 集合 - seq 可变序列

    val buffer1 = new ListBuffer[String]()
    val buffer2 = ListBuffer("a","b")


    buffer1.append("a","b","c","d","e")
    buffer1.insert(1,"f")
    buffer1.update(0,"g")
    buffer1(3) = "i"
    buffer1.remove(2)
    buffer1.remove(2,1)


    println(buffer1)
    println(buffer2)
  }

}
