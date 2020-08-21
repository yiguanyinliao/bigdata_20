package chapter05

object Scala24_Function_Lazy {

  def main(args: Array[String]): Unit = {

    // TODO Scala 函数 -惰性函数
    def fun9(): String = {
      println("function...")
      "zhangsan"
    }

    lazy val a = fun9()
    println("----------")
    println(a)
  }


}