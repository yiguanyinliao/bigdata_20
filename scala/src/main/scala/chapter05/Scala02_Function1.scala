package chapter05

object Scala02_Function1 {

  // TODO 在类中就是方法，可以进行重写和重载
  def main():Unit={

  }


  def main(args: Array[String]): Unit = {

    // 函数和方法
    // 函数的概念来自Scala
    // 方法的概率来自Java
    // Scala是完全面向函数式编程语言，所有方法就是函数
    // 一般情况下，将类中封装的功能函数称之为方法
    // 其他地方封装的功能称之为函数，就是函数可以声明到任意地方

    // 方法中可以声明函数
    // 函数中不能声明方法，因为方法只能声明在类中

    // 类中的函数就是方法，所以符合Java中方法的语法规则： 重写& 重载
    // 函数中共没有重写和重载的概念
    def test():Unit = {
      println("test")
    }

    test()



  }

}
