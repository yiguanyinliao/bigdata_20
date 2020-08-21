package chapter04

object Scala07_Flow_For2 {

  def main(args: Array[String]): Unit = {


    // 循环守卫
    for (i <- 1 to 5 if i %2 == 0) {
      println(i)
    }


    // 普通写法
    for (i <- 1 to 5) {
      if (i % 2 == 0) {
        println(i)
      }
    }

  }

}
