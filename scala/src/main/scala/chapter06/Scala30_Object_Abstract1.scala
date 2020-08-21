package chapter06

object Scala30_Object_Abstract1 {


  def main(args: Array[String]): Unit = {

    // TODO Scala 面向对象 - 抽象
    //  抽象类可以包含完整的方法
    //  我们的子类如果重写父类的抽象方法，那么只需要补充完整即可
    //  子类如果重写父类的完整方法，那么需要使用override关键字，明确这个方法

    val user = new ChileUser()
    user.test()


  }

  abstract class User{

    def test():Unit={    // 可以是完整的方法
      println("user..")
    }
  }


  class ChileUser extends User{

    override def test()={  // 如果重写父类的完整方法，需要使用override
      println("chile user..")

    }
  }

}

