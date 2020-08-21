package chapter05


object Scala19_Function_Abstract1 {

  def main(args: Array[String]): Unit = {

    // TODO Scala 函数 -控制抽象
    //  将代码作为参数传递到函数中
    def test(op: () => Unit): Unit = {

      op()
    }

    test {
      () =>
        println("zhangsan")
    }


    // 调用参数，单数由于参数没有声明小括号，调用时也不使用小括号
    def test1(op: => Unit): Unit = {

      op
    }
    test1{
      println("lisi")
    }

  }
}