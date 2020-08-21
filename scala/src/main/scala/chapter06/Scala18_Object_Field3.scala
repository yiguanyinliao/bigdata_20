package chapter06

import scala.beans.BeanProperty

object Scala18_Object_Field3 {


  def main(args: Array[String]): Unit = {

    // TODO Scala 面向对象 - 方法
    //  Scala 中方法其实就是函数，只是声明在类中，遵循方法的规则：重写&重载
    //  如果方法声明在class中，那么必须通过对象才能外部访问
//    new User().test()
    // 如果方法声明在object类中，可以直接通过类名直接访问，简单理解为静态static方法
    //    Scala18_Object_Field3.main()

    // TODO 默认情况下，类中存在哪些方法？
    //  1.object 类中要提供的方法，对象都有，能否使用需要考虑权限
    //  2.Scala提供的一些方法
    val user = new User()

    user.asInstanceOf[User] // 将对象转换为指定类型
    user.isInstanceOf[User] // 判断我们的对象是否为某个类型的实例
    user.eq(new User()) // 判断两个对象是否相等（编译时就是双等号）

    // 获取对象的类型信息(反射)
    val clazz: Class[_ <: User] = user.getClass
    val unit: Class[User] = classOf[User]


  }

//  class User {
//
//    override def equals(obj: Any): Boolean = {
//      if (obj.isInstanceOf[User]) {
////        val
//      }
//    }
//
//
//    def test() = {
//
//    }
//  }


}