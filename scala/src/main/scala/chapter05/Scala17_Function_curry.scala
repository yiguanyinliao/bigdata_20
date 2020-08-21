package chapter05

object Scala17_Function_curry {

  def main(args: Array[String]): Unit = {

    // TODO Scala 函数柯里化 -- 一般有闭包
    //  Scala针对柯里化函数有特殊的语法来使用

    def test(a:Int)(b:Int)(c:Int)={
      a+b+c
    }

    println(test(5)(10)(15))
  }
}
