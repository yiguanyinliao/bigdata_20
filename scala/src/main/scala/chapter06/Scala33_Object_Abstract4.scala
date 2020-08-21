package chapter06

object Scala33_Object_Abstract4 {


  def main(args: Array[String]): Unit = {

    // TODO Scala 面向对象 - 抽象

    new ChildUser()

    // TODO 打印结果为0
    //  1.子类可以重写父类的完整属性
    //  2.println打印的时候，其实是访问的属性的getff
    //    get方法时成员方法，具有动态绑定技术，会执行ChildUser的get方法
    //  3.类的初始化和类的加载
    //    类的加载完成后执行类的初始化
    //  4.父子类的初始化顺序
    //    父类初始化完成后才会执行子类的初始化操作

  }

  class Person{
    var name:String= _   // _ 代替了类的初始化
  }

  /*
  // 父类
  class User{

    age = 20
    age()  // get() => 成员方法=> 动态绑定 => 执行子类的age方法
  }
   */

  abstract class User{
    val age:Int = 20
    println(age)


  }

    /*

    // 子类
    class ChildUser extecds User{
      age = 0 默认值
      子类初始化后将age = 30
      age() // 返回age


    }
   */

  class ChildUser extends User{
    override val age:Int = 30

  }

}

