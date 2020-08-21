package chapter06

object Scala37_Object_Interface2 {


  def main(args: Array[String]): Unit = {

    // TODO Scala 面向对象 - 特质

    val operate: Operate = new MySql()
    operate.oper()

    // 在scala编译时，会将特质编译interface
    // 一个类混入特质时，等同于在编译时实现接口


  }

  // 声明特质
  trait Operate{
    // 声明抽象方法
    def oper():Unit
  }

  // 一个类混入特质

  class MySql extends Operate{

    // 重写方法
    //   1.重写抽象方法：不错完整
    //   2. 完整方法：需要增加override方法
    override def oper(): Unit = {
      println("执行mysql操作")
    }
  }

}

