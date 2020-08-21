package chapter08

object Scala07_Match5 {

  def main(args: Array[String]): Unit = {


    // TODO 模式匹配

    // TODO 匹配对象
    //  这里的匹配对象不是用于匹配对象的内存地址，而是匹配对象的属性是否相等
    //  普通对象是无法完成模式匹配的，如果对象想要进行模式匹配，必须提供unapply方法
    //  apply <=> unapply
    //  apply => param => object
    //  unapply => object => param
    //  对象在模式匹配时，会根据对象的unapply方法获取到对象的属性
    //  然后判断属性值和给定的属性是否一致，如果一致，就匹配成功。
    val user: User = User("zhangsan", 11)
    val result = user match {
      case User("zhangsan", 11) => "yes"
      case User("lisi", 11) => "yes"
      case User("wangwu", 11) => "yes"
      case _ => "no"
    }
    println(result)

  }



  class User(val name: String, val age: Int) {

  }

  object User {
    def unapply(user: User): Option[(String, Int)] = {
      if (user == null)
        None
      else
        Some(user.name, user.age)
//        Some(user.name, user.age+1)  // 根据unapply方法获取属性是否一致
    }

    def apply(name: String, age: Int) = new User(name, age)
  }

}

