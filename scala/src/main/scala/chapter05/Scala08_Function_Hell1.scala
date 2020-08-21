package chapter05

object Scala08_Function_Hell1 {


  def main(args: Array[String]): Unit = {

    // TODO Scala 函数式编程 -地狱难度

    // TODO 函数是Scala中最重要的内容，可以将函数看做对象来使用

    // TODO 1.可以将函数看做对象，就可以将这个对象赋值给其他变量来说明
    //  函数就可以给变量来使用

    def test(): Unit = {
      println("test...")
    }


    def test1(name: Int): String = {
      name.toString
    }

    def test2(name: Int, j: Int): String = {
      name.toString
    }


    def test3(name: Int, j: String): Int = {
      name
    }

    // TODO 将函数赋值给变量，那么这个变量就是一个函数，就可以调用
    //  如果函数参数列表里没有参数，调用时可以省略小括号
    // TODO 在下面的代码中，我们需要执行我们的test函数，将函数的返回结果赋值给t
    //  由于test函数的返回值是Unit，所以变量t赋值为Unit，所以不能调用

    val t = test
    val t2 = test()

    val f1 = test1 _   //(Int) => String
    val f2 = test2 _   //(Int,Int) => String
    val f3 = test3 _  //(Int,String) => Int

    // TODO 如果想要将函数作为一个整体的对象赋值给变量，需要使用 _
    //  当使用下划线时
    //  变量就会变成函数类型，可以被调用
    //  这里的函数类型为:() => Unit

    val t1 = test _

    // 函数调用
    t1()
    //    test()
    //    println(t)

    f1
    f2
    println(f3(1, "zhangsan"))


    // TODO 如果不使用下划线，也将函数作为整体赋值给变量
    //  之前不加下划线之所以会执行函数，因为需要执行函数后才能推断出变量的类型
    //  如果不想要执行函数，就必须明确变量的类型，不能省略,也不能使用字段推断
    //  如果使用明确使用类型声明，那么赋值的函数需要匹配类型
    val f11: (Int) => String = test1
    println(f11(20))

    val f22: (Int,Int) => String = test2
    println(f22(10, 20))


    val f33: (Int,String) => Int = test3
    println(f33(50, "zhangsan"))

    // TODO 2.可以将函数作为参数传递给函数


    // TODO 3.可以将函数作为返回值在函数值返回


    def fun1(name:String)={
      name
    }
    def fun2()={
      println("=====")
    }

    println(fun1("*******"))

  }

}
