package chapter05

object Scala06_Function_NightMero {
  def main(args: Array[String]): Unit = {

    def fun1():Unit={
      return "zhangsan"
    }
    println(fun1())

    // TODO 如果函数声明Unit，那么函数体中有return也不起作用



    // TODO 如果函数体中存在return，返回值类型不能省略
    def fun2():Unit={
      return "zhangsan"
    }


    // TODO 如果函数返回值类型为Unit，并且想省略Unit，可以采用特殊的方式，将等号省略
    def fun3()={
      "zhangsan"
    }


    // TODO 省略等号表示没有返回值
    //  一般将这一的函数称之为过程函数
    def fun33(){
      "zhangsan"
    }

    println(fun3)
    println(fun33)


    // TODO 如果不关心函数名称，只关心执行逻辑，那么def、函数名称可以省略
    //  将这样的函数称为匿名函数
    //  如果不使用def来声明函数，需要使用特殊颜文字来声明
    //  匿名函数没有名字，无法直接调用，需要将匿名函数赋值给变量来调用
//    def fun4()={
//      "zhangsan"
//    }

    val a = () => {
      println("function_zhangsan")
    }
    // TODO 将匿名函数赋值给变量，那么这个变量的类型就是函数，所以可以调用
    a()

  }

}
