package chapter06

object Scala32_Object_Abstract3 {


  def main(args: Array[String]): Unit = {

    // TODO Scala 面向对象 - 抽象

    // TODO 如果子类重写父类的完整属性，那么需要使用override修饰符
    //  子类重写父类的属性，那么重写的属性必须是val修饰
    //  如果可以将var变量重写，在使用时会产生歧义，需要改为val

    val user = new ChildUser()
//    println(user.name)

    user.test()


  }

  abstract class User{
    val name:String = "zhangsan"  // 完整的属性 使用var有歧义，所以需要使用val

    def test()={
//      name = "wangwu"  // setName()=>成员方法=>动态绑定技术
      println(name)
    }

  }

  class ChildUser extends User{
    override val name:String = "lisi"  // 如果父类是完整的属性，需要添加override，明确是重写方法
  }

}

