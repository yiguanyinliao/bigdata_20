package chapter10

object Scala08_Transform7{


  def main(args: Array[String]): Unit = {

    import chapter09._

    val user = new User()
    user.insert()
    user.update()


  }
  class User {
    def insert(): Unit = {
      println("insert user...")
    }
  }

}