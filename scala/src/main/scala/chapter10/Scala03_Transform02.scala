package chapter10


object Scala03_Transform02 {


  def main(args: Array[String]): Unit = {


    // TODO Scala 中的隐式转换功能可以实现功能扩展，非常强大
    // TODO 所谓的隐式转换就是类型转换，而且这个转换是有编译器完成的
    implicit def transform(user:User):Ext = {
      new Ext()
    }


    val user = new User()    // 隐式转换功能演示
    user.insert()
    user.update()

//    ***********************************
//    val user = new User() with Exit     // 动态混入
//    user.insert()
//    user.update()
//    ***********************************

    }

  class Ext{
    def update(): Unit ={
      println("update user.....")
    }

  }

  //    ***********************************
//  trait Exit{
//    def update(): Unit ={
//      println("update user.....")
//    }
  //    ***********************************
  class User {

    def insert(): Unit = {
      println("insert user.....")
    }


  }


}


