package chapter06

object Scala31_Object_Abstract2 {


  def main(args: Array[String]): Unit = {

    // TODO Scala 面向对象 - 抽象

    // TODO 抽象属性：Scala中不完整的属性就是抽象属性
    //    如果一个类中包含抽象属性，那么这个类也是不完整的，需要声明为抽象类
    //    抽象类无法构建对象，需要子类继承后再构建对象
    //    如果子类继承抽象类，需要将不完整的内容进行补充，才可以创建对象，否则子类也要声明为抽象类

    // 子类应该讲抽象属性进行补充完整，将属性初始化

    // 抽象属性在编译时就不会生成属性，而是生成属性对象的set、get的两个抽象方法
    // 子类继承抽象类，补全抽象属性，等同于增加属性将set、get两个抽象方法进行重写

    val user = new ChildUser()
    println(user.name)


  }

  abstract class User{
    // TODO 抽象属性
    var name:String

  }

  class ChildUser extends User{
    var name:String = "zhangsan"
  }

}

