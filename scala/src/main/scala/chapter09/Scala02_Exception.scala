package chapter09

object Scala02_Exception {

  def main(args: Array[String]): Unit = {


    // TODO Scala异常和Java的异常的语法基本一致
    //  Scala中的异常不区分编译时异常或运行时异常，所以的异常都不需要显示的处理
    //  如果想要处理，那么就采用try catch
    //  scala中catch语法和Java不太一样，类似于 模式匹配
    //  scala 中异常处理采用的是类型的模式匹配，所以异常范围大的放在前面是可以的（不推荐使用）
    //  如果捕捉异常时，类型不匹配，这个时候发生的错误不是MatchError，而是异常本身
    //  Scala函数声明中没有抛出异常的概念，所以没有throw关键字，但是在逻辑上可以throw异常
    //  Scala中finally语法和java语法一致
    //  异常的处理在当前编码风格中已经不仅仅局限于错误，也可以应用于逻辑操作
    //  如果想要在scala代码中明确方法需要抛出异常，可以采用注解的方式 @throws[Exception]。

    try {
//      val i = 0
//      val j = 10 / i
      val array = Array(1, 2)
      println(array(5))
    } catch {
//      case e: Exception => println("异常。。。")
      case e: ArithmeticException => println("算术异常。。。")
      case e: Exception => e.printStackTrace()
    }finally {

    }

    @throws[Exception]   // 用 try catch 也可以
    def test()={

    }

  }

}
