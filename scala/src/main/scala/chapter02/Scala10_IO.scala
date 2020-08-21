package chapter02

object Scala_IO {
  def main(args: Array[String]): Unit = {
    val line:String = scala.io.StdIn.readLine()

    println(line)
  }
}
