package chapter06

object Scala38_Object_Interface3 {


  def main(args: Array[String]): Unit = {

    // TODO Scala 面向对象 - 特质

    val operate: Operate = new MySql()
    operate.oper()

    // 在scala编译时，会将特质编译interface
    // 一个类混入特质时，等同于在编译时实现接口
    // trait中可以声明完整的方法
    // 混入特质的类可以重写或调用这个方法
    // 和java中的抽象类很像

    // TODO 可以将特质理解为java中抽象类和接口的融合体
    //  特质中可以有完整的方法也可以有抽象的方法
    //  特质中如果当成抽象类看，可以继承父类


  }

  // 声明特质
  trait Operate extends Exception{  // 特质可以继承类，java中接口是不可以的

    // 抽取的特征不一定时抽象的，也可能是完整的
    def oper():Unit={
      println("操作数据")

    }
  }

  // 一个类混入特质，将特质中的抽象方法补全

  class MySql extends Operate{

    // 重写方法
    //   1.重写抽象方法：不错完整
    //   2. 完整方法：需要增加override方法
    override def oper(): Unit = {
      println("执行mysql表格中")
      super.oper()
    }
  }

}

