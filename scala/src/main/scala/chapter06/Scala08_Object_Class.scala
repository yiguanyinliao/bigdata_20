package chapter06

object Scala08_Object_Class {


  def main(args: Array[String]): Unit = {

    // TODO Scala 面向对象编程 - 类
    // TODO class 和 object的区别
    //  1.class和java中的class没有区别，表示类型定义
    //    编译时会产生对应的类文件
    //  2.object 关键字可以声明类，编译时会产生两个类文件
    //    一个是当前的类文件，一个是当前类$ class 文件
    //    scala是完全面向对象的语言，所以摒弃了面向对象的语法
    //    其中就包括static语法，所以scala中没有静态语法
    //    为了和java语法兼容，采用了特殊的方式替代静态语法
    //    使用object关键字声明的class中的方法和属性可以通过类名直接访问

    // 使用object关键字会产生单例对象，然后通过这个单例对象来访问方法和属性
    // 但是这个单例对象我们无法直接访问，所以通过object关键字修饰后，可以通过
    // 修饰后的类名直接访问，相当于模拟 静态 对象
    // 一般会将object声明时创建的单例对象称之为伴生对象，伴随着类产生的单例对象
    Scala08_Object_Class.test()


    // TODO 静态语法是通过类型直接访问静态属性或者静态方法


  }
  def test()={
    println("test........")
  }

}