package chapter06

object Scala25_Object_Instance2 {


  def main(args: Array[String]): Unit = {

    // TODO Scala 面向对象 - 构造方法
    // TODO 子类构造对象是，会首先构造父类对象
    //  子类在类初始化之前，必须保证父类已经完成类的初始化


    //问题： 如果父类构造方法有参怎么办
    //       子类继承父类是，可以直接在父类的后面传递参数
//    val user = new User()
    val user = new User("lisi")


  }
//  class Person{
//    println("person init...")
//
//  }
//  class User() extends Person{
//    println("user init...")
//  }

//  class Person(name:String){
//    println("person init...")
//
//  }
//  class User() extends Person("zhangsan"){
//    println("user init...")
//  }

  class Person(name:String){
    println("person init..." + name)

  }
  class User(name:String) extends Person(name){
    println("user init...")
  }



}

