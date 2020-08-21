package chapter06

object Scala24_Object_Instance1 {


  def main(args: Array[String]): Unit = {

    // TODO Scala 面向对象 - 构造方法
    //  java中构造方法可以重载，声明多个

    // TODO Scala 中构造方法只有一个吗？
    //  不是只有一个，scala中提供了两种不同类型的构造方法
    //  1.主构造方法：在类名后加()的构造方法称为主构造方法，主要功能是完成类的初始化
    //  2.辅助构造方法：为了完成类的初始化的辅助功能而提供的我们的构造方法
    //    辅助构造方法必须调用主构造方法，完成类的初始化
    //    声明方式：def this() = {}
//    val user = new User()
//    val user1 = new User(name="zhangsan")
//    val user2 = new User(name="zhangsan",10)
    val user3 = new User(name="zhangsan",10,"男")

  }

  class User(){ // 加()是主构造方法
    println("user init.....")
    def this(name: String)={
      this()   // 调用主构造方法完成类的初始化
      println("1111")
    }

    def this(name: String,age:Int)={
      this()   // 调用主构造方法完成类的初始化
      println("2222")

    }

    def this(name: String,age:Int,sex:String)={
      this(name)   // 调用主构造方法完成类的初始化
      println("3333")
    }
    println("44444")

  }
}

