package chapter05

object Scala14_Function {


  def main(args: Array[String]): Unit = {


    // TODO 函数式编程 --闭包
    //  函数使用外部的变量是，由于作用域的问题，本来无法访问
    //  但是痛殴操作可以将这个变量包含到函数的内部，形成闭合的效果，称之为闭包
    //  可以改变变量的生命周期
    //  闭包编译的方式和版本相关
    //  2.12版本会在编译过程中采用改变函数声明的方式来实现闭包
    //  2.11会在编译过程中给外部变量生成一个匿名函数类


    // TODO 闭包的场合
    //  1.所有的匿名函数都有闭包
    //  2.将函数赋值给变量会有闭包
    //  3.将内部函数返回到外部来使用会有闭包

    // TODO Spark框架会对闭包
    def sum(i: Int) = {
      def inner(j: Int): Int = {
        i + j
      }

      inner _
    }

    println(sum(10)(20))
  }

}
