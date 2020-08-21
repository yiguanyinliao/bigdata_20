package chapter05

object Scala22_Function_Recursion2 {

  def main(args: Array[String]): Unit = {

    // TODO Scala 函数 -递归
    //  递归是Scala中用户使用最多的数据处理的算法

    // 普通递归
    // 编译器不做任何优化，其实就是递归
    def test():Unit = {
      test()
      println("test....")

    }

    test()

    // 尾递归
    // 编译器会将尾递归优化成while循环
    def test1():Unit = {
      println("test....")
      test1()

    }

//    test1()




  }
}
