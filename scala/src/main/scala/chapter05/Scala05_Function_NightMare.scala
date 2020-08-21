package chapter05

object Scala05_Function_NightMare {

  def main(args: Array[String]): Unit = {


    // TODO Scala 函数式编程 -- 噩梦难度
    // Scala 中有至简原则：能省则省
    // 只要是编译器可以自行推断，就可以省


    def fun1(name: String): String = {
      return name
    }

    // TODO 1.当函数需要返回值的时候，可以采用函数体中满足条件的最后一行代码作为返回值
    //  所以可以省略return


    def fun2(name: String): String = {
      name
    }

    println(fun1("zhagnsan"))
    println(fun2("zhagnsan"))

    // TODO 2.如果编译器可以推断出返回值类型，那么:和返回值类型可以省略

    def fun3(name: String)  = {
      name
    }

    println(fun3("zhangsan"))

    def fun33(name: String) ={
      val age = 20
      if (age == 20) {
        name
      } else{
        1
      }
    }

    // TODO 3.如果函数体逻辑代码只有一行，大括号可以省略

    def fun4(name: String)  = name
    println(fun4("lisi"))


    // TODO 4.如果参数列表没有参数，小括号可以省略
    def fun5()  = "wangwu"
    println(fun5())
    println(fun5) //调用函数时去掉（）

    def fun55  = "wangwu"

    println(fun55) // 如果函数声明时省略了小括号，在调用时候也要省略小括号



  }


}
