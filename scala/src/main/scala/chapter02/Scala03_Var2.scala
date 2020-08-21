package chapter02

import chapter02.bean.{Emp, User}

object Scala03_Var2 {
  def main(args: Array[String]): Unit = {


    // scala 中变量的声明必须显示的初始化
    var userName : String = "zhangsan"


    System.out.println("年龄 = " + User.age)

    // 类是一定会被加载的，静态代码块不一定会被加载
    System.out.println("年龄 = " + Emp.age)


  }
}
