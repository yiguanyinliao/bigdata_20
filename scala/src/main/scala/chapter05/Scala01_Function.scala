package chapter05

object Scala01_Function {


  def main(args: Array[String]): Unit = {

    // TODO Scala 函数式编程
    // 在函数式编程领域，万物皆函数！
    // 1、 函数就是方法，方法就是函数
    // 2、 函数的声明
    //  def 函数名（参数列表）：返回值类型={函数体}
    // 3、函数的调用
    //  函数名（参数）

    test("hello scala")

  }

  // 参数列表中的参数声明
  // 参数名： 参数类型
  def test(s: String): Unit = {
    println(s)
  }

}
