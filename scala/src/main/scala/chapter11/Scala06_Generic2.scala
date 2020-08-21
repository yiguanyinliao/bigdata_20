package chapter11

import chapter11.bean.{Child, Parent, User}

object Scala06_Generic2 {

  def main(args: Array[String]): Unit = {

    // TODO Scala - 泛型

    val list = List(1,2,3,5)

    val userList = List(
      new User(), new User(), new User()
    )
    val parent: Parent = userList.reduce[Parent](   // reduce 里默认是下限
      (x, y)=>x
    )
    println(parent)

//    val child: Child = userList.reduce[Child](   // (X)
//      (x, y)=>x
//    )
//    println(child)

  }

}
