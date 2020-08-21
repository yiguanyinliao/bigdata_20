package chapter02

object Scala03_Var1 {
  def main(args: Array[String]): Unit = {

    // Scala中不能使用final关键字
    // Scala声明变量可以使用val关键字
    // Scala中使用编译器的语法限制实现了val操作，和final不太一样


    // 使用val和var声明的变量的区别：
    //1、使用var声明的变量可以概念变量的值
    //2、使用功能val声明的变量一旦出事话无法改变变量的值
    // val和var都是声明变量，只是变量的值是否可以更改

    // val 声明的变量一般称为不可变量，不是常量
    // 苍龙一般为： 1，"abc", 'c', true
    val userName : String = "zhangsan"
//    userName = "lisi"
    println(userName)
  }

}
