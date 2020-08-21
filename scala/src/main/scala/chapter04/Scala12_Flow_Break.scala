package chapter04

import scala.util.control.Breaks.{break,breakable}

object Scala12_Flow_Break {

  def main(args: Array[String]): Unit = {
    var age = 10

    breakable{

      while (age < 20 ) {

        if (age == 15) {
          // 跳出循环
          // break方法会抛出异常，终止程序
          break()

        }
        println("age = " + age)
        age += 1

      }
    }

  }

}
