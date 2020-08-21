package chapter05

import scala.util.control.Breaks

object Scala18_Function_Abstract {

  def main(args: Array[String]): Unit = {

    // TODO Scala 函数 -控制抽象

    // breakable 其实是一个方法（函数）
    // 函数（参数列表）
    // TODO 如果参数是多行的场合，可以使用大括号代替()
    //  将一段代码逻辑作为参数进行传递
    //  一般情况下，控制抽象应用于框架或者应用本身
    Breaks.breakable(
      for (i <- 1 to 5) {

        if (i == 3) {
          Breaks.break()
        }

        println("i= " + i)
      }

    )

    println("main 方法执行完毕、、、、")


  }
}
