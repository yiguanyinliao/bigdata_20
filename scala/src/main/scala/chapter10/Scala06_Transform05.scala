package chapter10

object Scala06_Transform05 {


  def main(args: Array[String]): Unit = {

    // TODO 隐式转换 - 隐式类

//    implicit def transform(user:User):UserExt={
//      new UserExt()
//    }

    //TODO Scala 版本升级后，可以将扩展类和隐式函数合二为一，使用一个特殊的语法（隐式类）实现同样功能
    // 隐式类：在类的前面使用关键字 implicit
    // 所谓的隐式转换就是类型的转换
    // 隐式类不能作为顶级对象（放在伴生类或着类之外）来使用
    // 隐式类的构造方法只能有一个参数


    val user = new User()
    user.insert()
    user.update()

  }


  implicit class UserExt(user: User){   // 需要添加构造参数
    def update(): Unit ={
      println("update user ...........")
    }
  }


  class User{
    def insert(): Unit ={
      println("insert user.........")
    }


  }
}


