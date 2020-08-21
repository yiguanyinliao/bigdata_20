package chapter02

import java.io.{File, PrintWriter}


class Scala12_write {

  def main(args: Array[String]): Unit = {
    val writer = new PrintWriter(new File("test.txt"))
    writer.write("hello Scala")
    writer.close()
  }

}
