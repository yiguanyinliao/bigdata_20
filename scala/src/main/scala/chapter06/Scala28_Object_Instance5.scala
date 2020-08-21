package chapter06

object Scala28_Object_Instance5 {


  def main(args: Array[String]): Unit = {

    // TODO Scala 面向对象 - 构造对象
    //  1.new => 等同于调用类的构造方法
//    val user = new User()
    //  2.apply => 等同于调用伴生对象的apply方法
//    val user1 = User()  // 私有构造方法不能生成对象
    //  3.反射
    val clazz:Class[User] = classOf[User]
    val user2:User = clazz.newInstance()
    //  4.反序列化


  }

  // 在构造方法前增加private关键字，表示构造方法私有化
  class User private(){

  }
  object User{

  }
}

