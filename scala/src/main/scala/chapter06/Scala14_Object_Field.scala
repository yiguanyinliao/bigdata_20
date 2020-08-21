package chapter06

object Scala14_Object_Field {


  def main(args: Array[String]): Unit = {

    // TODO Scala 面向对象 - 属性
    //  1.类的属性其实就是类中的变量，在类的外部可以通过对象访问
    //  属性的声明规则和变量的声明规则完全一样
    //    1.1 可以使用var、val来声明
    //    1.2 属性的类型可以省略
    //    1.3 变量在内部使用是，有作用域的概念
    //      属性在外部使用时，不存在作用域的概念，存在访问权限的概念（声明在var或者val的前面）
    //    1.4 属性的默认值
    //      Java中一般不给属性赋值，而是有编译器自动完成属性的初始化
    //      Sacla 中的属性必须是显示的初始化，不能默认初始化
    //      如果想要Scala和Java中一样，可以使用下划线完成属性初始化
    //      使用val声明的属性不能使用下划线进行赋值
    val user = new User()
    user.name = "lisi"

  }
  class User{

    // TODO 属性声明方式
    //    访问权限 val | var 属性名：属性类型 = 属性值
    //    访问权限表示属性是否能够被访问，默认情况下，访问权限可以不声明，默认是公共的


//    var name:String = "zhangsan"
    var name:String = _  // 使用 _ 可以完成属性初始化，和Java的一样
//    val age:Int = 20
//    val sex = "男"
    var age:Int = _  // 必须使用var
//    var sex = _
  }


}