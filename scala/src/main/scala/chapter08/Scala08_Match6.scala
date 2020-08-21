package chapter08

import chapter08.Scala07_Match5.User

object Scala08_Match6 {

  def main(args: Array[String]): Unit = {


    // TODO 模式匹配

    // TODO Scala 为了解决匹配对象的繁琐的问题，提供了一个新的语法来简化操作
    //  专门提供模式匹配的类称之为样例类
    //  样例类会自动生成apply方法和unapply方法
    //  样例类自动实现可序列化接口
    //  样例类的构造参数自动val声明。
    val user: User = User("zhangsan", 11)
    val result = user match {
      case User("zhangsan", 11) => "yes"
      case User("lisi", 11) => "yes"
      case User("wangwu", 11) => "yes"
      case _ => "no"
    }
    println(result)

  }

  // TODO 样例类在类的前面加上case关键字
  case class User(name: String, age: Int) {

  }

}

