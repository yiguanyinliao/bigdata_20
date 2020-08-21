package chapter06

object Scala11_Object_Class2 {


  def main(args: Array[String]): Unit = {

    // TODO Scala 面向对象编程 - 类
    // TODO 使用object声明的伴生对象
    //  使用class声明的伴生类
    //  使用class就和java一样，声明普通类
    //  Scala和java一样，也存在父子类，子类需要继承父类
    //  Scala声明类后，可以直接构建对象，构建时下括号可以省略

    val user1 = new User()
    val user2 = new User
    // TODO 如果不使用new，直接获取User类，其实获取到的伴生对象
    val user3 = User
    println(user1)
    println(user2)
    println(user3)



  }
  class Parent{

  }
  class User extends Parent{

  }
  object User{

  }


}