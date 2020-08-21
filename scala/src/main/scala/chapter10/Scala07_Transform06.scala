package chapter10

import chapter10.Scala07_Transform06.User

//object Scala07_Transform06 extends Parent with TraitTest {
//object Scala07_Transform06 extends TraitTest {
object Scala07_Transform06 extends Parent {


  def main(args: Array[String]): Unit = {

    // TODO Scala 隐式转换的作用域
    //  1.相同作用域
    //  2.上级作用域（ParentClass,package,trait）
    //  3.不能同时存在两个等级别的上级.
    val user = new User()
    user.insert()
    user.update()

  }

  class User{
    def insert(): Unit ={
      println("insert user......")
    }
  }

//  implicit class User1(user: User){
//    def update(): Unit ={
//      println("update user1......")
//    }
//  }

}

//trait TraitTest{
//  implicit class User3(user: User){
//    def update(): Unit ={
//      println("update user3......")
//    }
//  }
//}

//object  TraitTest {
//  implicit class User3(user:User) {
//    def update(): Unit = {
//      println("update user3...")
//    }
//  }
//}

class Parent{
//  implicit class User2(user: User){
//    def update(): Unit ={
//      println("update user2......")
//    }
//  }

}
//
//class Parent extends TraitTest {
//  implicit class User2(user: User){
//    def update(): Unit ={
//      println("update user2......")
//    }
//  }

//}

object Parent {
//      implicit class User2(user:User) {
//          def update(): Unit = {
//              println("update user2...")
//          }
//      }
}