package chapter05

object Scala12_Function_Hell5 {


  def main(args: Array[String]): Unit = {

    // TODO 3.可以将函数作为返回值在函数值返回

    def fun(): Unit = {
      println("fun....")
    }

    // TODO 函数会将满足条件的最后一行逻辑代码作为返回值
    //  可以将一个函数作为返回值返回
    //
    //    def test()={
    //      fun
    //    }
    //    test()
    def test() = {
      fun _
    }


    // 调用函数
    val f = test()
    f()

    // 第二种调用方法
    test()()

    // TODO 可以将函数作为返回值返回
    //  一般将函数作为返回值返回时，使用嵌套函数

    //    def outer()={
    //      def inner():Unit = {
    //
    //        println("inner function")
    //      }
    //
    //      inner _
    //    }
    //
    //    outer()()


    def outer(): () => Unit = {
      def inner(): Unit = {

        println("inner function")
      }

      inner // 不加下划线方式
    }

    outer()()


    // TODO 传参
    def sum(i: Int) = {
      def innerSum(j: Int): Int = {
        i + j
      }

      innerSum _
    }

    println(sum(10)(20))


    // TODO 加运算逻辑
    // operator(10)(20)(xxxx) => 20,100
    def operator(x: Int) = {
      def inner(y: Int) = {
        def oper(f: (Int, Int) => Int): Int = {
          f(x, y)
        }

        oper _
      }

      inner _
    }

    println(operator(10)(20)((x, y) => {
      x + y
    }))
    println(operator(10)(20)(_ + _))
    println(operator(10)(20)(_ * _))


  }

}
