package chapter02

import scala.io.BufferedSource

object Scala_IO_FIle {
  def main(args: Array[String]): Unit = {

    // Scala 输入输出
//    val source:BufferedSource = scala.io.Source.fromFile("")
//    val lines: Iterator[String] = source.getLines()

    val a = new String("abc")
    val b = new String("abc")

    println(a == b)
    println(a.equals(b))

  }

}
