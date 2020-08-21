package chapter06

import scala.beans.BeanProperty

object Scala17_Object_Field2 {


  def main(args: Array[String]): Unit = {

    // TODO Scala 面向对象 - 属性

    // 类中声明属性不仅仅声明属性，声明属性其实在编译时会声明私有的属性和两个公共的方法（set，get）
    val user = new User()

    // name_$eq => name_=()
    // 给Scala的对象的属性赋值的时候，等同于调用了属性的set方法
    user.name = "lisi"
//    user.age = 30

    // 访问Scala对象的属性时，等同于调用了属性的get方法
    println(user.name)

    // Scala中声明的属性并没有遵循Bean规范，无法在大多数框架中直接使用
    // Scala 如果希望类符合Bean规范，可以在属性钱增加对对应的注解
    user.setFamily("02")
    user.getFamily

  }
  class User{

    var name:String = _


    // 如果类的声明是私有的，编译时会生成set，get的私有方法，所以不能调用
    private var age:Int = 20

    // 如果类的属性声明时使用val，那么编译时，会自动生成final修饰符，那么就不会生成属性对应set方法，所以无法赋值
    val sex = "男"

    // 增加注解符合bean规范
    @BeanProperty var family = "01"

  }


}