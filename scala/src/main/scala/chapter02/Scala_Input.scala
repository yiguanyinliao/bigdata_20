package chapter02

import scala.io.{BufferedSource, Source}

object Scala_Input {

  def main(args: Array[String]): Unit = {

    // Scala 输入输出
    // 读取文件需要设定文件路径
    // 1. 绝对路径 : 不可改变的路径
    //       本地路径：file:///c:/test.txt
    //       网络路径: http://localhost:8080/test.txt
    // 2. 相对路径 : 可以改变的路径, 存在一个基准路径，以这个路径为参考，查找对应的文件
    //       IDEA中的相对路径就是以Project的根路径为基准路径
//    val source: BufferedSource = scala.io.Source.fromFile("input/word.txt")
    val source: BufferedSource = scala.io.Source.fromFile("input/word.txt")

    val lines: Iterator[String] = source.getLines()

    while ( lines.hasNext ) {
      println(lines.next())
    }

    source.close()



    Source.fromFile("test.txt" ).foreach{
      print
    }
  }
}
