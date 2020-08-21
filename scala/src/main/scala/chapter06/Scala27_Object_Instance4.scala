package chapter06

object Scala27_Object_Instance4 {


  def main(args: Array[String]): Unit = {

    // TODO Scala 面向对象 - 构造方法
    //  为什么给构造方法传参？
    //  1.在类的初始化过程中需要使用外部的参数
    //  2.属性的初始化来自外部的参数

    // 类的属性不能和类的构造参数重名
    // 类的构造参数一般用于属性赋值，那么就导致属性值和参数值一致，冗余
    // scala优化了参数的设定，将构造参数作为类的属性存在,需要使用特殊语法（var）

    val user = new User("zhangsan")
    user.name = "lisi"
    println(user.name)

  }

  class User(var name:String){  // 加var 将构造函数的参数作为属性存在，使用val不能做修改操作
//    var userName:String = name

  }
}

