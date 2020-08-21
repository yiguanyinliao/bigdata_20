
import chapter10.Scala08_Transform7.User

package object chapter09 {
  implicit class User4(user:User) {
    def update(): Unit = {
      println("update user4...")
    }
  }

}
