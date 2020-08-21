
import chapter10.Scala07_Transform06.User


package object chapter10 {

  implicit class User4(user: User){
    def update(): Unit ={
      println("update user4 ...")
    }
  }

}
