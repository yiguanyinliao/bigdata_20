package chapter06

object Scala23_Object_Instance {


  def main(args: Array[String]): Unit = {

    // TODO Scala 面向对象 - 构造方法
    //  java中每一个类默认提供无参构造方法，可以不需要显示的声明，也可以显示的声明
    //  scala 中的构造方法不是和类名一致，和类名一致的方法其实是普通方法
    //  scala 默认提供无参的构造方法，但是也可以显示的声明
    //  scala 是一个完全面向函数是编程的语言，万物皆函数，类其实也是函数
    //  所以类名的后面是可以增加小括号的

    // 构造方法执行时，会完成类的初始化操作
    // 构造方法如果需要传递参数，那么不能省略小括号
//    val user = new User()
////    user.User()
//    println(user)

    val user = new User("zhangsan") // 有参的构造方法
    //    user.User()
    println(user)


  }
//  class User{
//    def User()={
//      println("user...")
//    }
//
//  }

//  class User(){  // 构造方法
//    // 函数体 $ 类的主体
//    println("user...")
//
//
//  }


  class User(name:String){  // 构造方法
    // 函数体 $ 类的主体
    println("user...")


  }

}

