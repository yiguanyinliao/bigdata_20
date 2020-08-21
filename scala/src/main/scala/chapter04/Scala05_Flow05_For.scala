package chapter04

object Scala05_Flow05_For {


  def main(args: Array[String]): Unit = {

    // scala for循环
    // scala 和java的循环不太一样，循环方式类似于增强for循环

    val list = 1 to 5

    for (i:Int<-list) {
      println(i)
    }

  }

}
