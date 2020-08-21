package chapter06

object Scala13_Object_Class4 {


  def main(args: Array[String]): Unit = {

    // Parent, User 在object中声明，那么表示内部类

  }

//  class Parent {
//
//  }
//
//  class User extends Parent {
//
//  }
//
//  object User {
//
//  }


}

// 把这几个类放到object外面，每个class会产生自己的类文件
// 在Scala中可以声明多个公共类（访问权限）
// 并且不需要和源码文件名一致

class Parent {

}

class User77 extends Parent {

}

object User77 {

}
