package chapter11

import chapter11.bean.{Child, User,Parent}

object Scala04_Generic {

  def main(args: Array[String]): Unit = {

    // TODO Scala - 泛型

    // TODO Java中的泛型和Scala中的泛型基本一致
    //  Scala中泛型在类或者方法后面增加[]
    //  1.泛型不可变、
    val t1 : Test1[User] = new Test1[User]()
//    val t2 : Test1[User] = new Test1[Parent]()    // (X)
    val t3 : Test1[User] = new Test1[Child]()

    // TODO 2.Scala为了让泛型使用起来方便，对泛型进行了优化，让类型和泛型作为整体来使用
    //  协变：List[泛型1] = List[泛型2]
    //  如果泛型2是泛型1的子类型，那么就让List[泛型2]作为List[泛型1]的子类型使用
    //  协变是在泛型前增加 +
    //  逆变：如果泛型2是泛型1的父类型，那么就让List[泛型2]作为List[泛型1]的子类型使用
    //  逆变是在泛型前增加 -。

    val t4 : Test2[User] = new Test2[User]()
    val t5 : Test2[User] = new Test2[Parent]()
//    val t6 : Test2[User] = new Test2[Child]()     // (X)

  }

  class Test1[+T]{

  }

  class Test2[-T]{

  }

}
