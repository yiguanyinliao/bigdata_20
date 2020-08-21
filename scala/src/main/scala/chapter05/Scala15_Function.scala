package chapter05

object Scala15_Function {


  def main(args: Array[String]): Unit = {

    // 函数式编程 -- 闭包

    val a = 10
    def test()={
      println(a)
    }
    test()

  }

}
