package chapter11

import chapter11.bean.{Child, Parent, User}

object Scala07_Generic3 {

  def main(args: Array[String]): Unit = {

    // TODO Scala - 上下文限定,主要用于隐式转换和泛型结合的操作
    def f[A : Test](a: A) = println(a)

//    implicit val test : Test[User] = new Test[User]
    implicit val test : Test[Child] = new Test[Child]

    // 如果出现(...)标红线，原因是隐式参数没有找到隐式变量
//    f( new User() )
    f( new Child() )

  }

  class Test[T] {
  }

}
