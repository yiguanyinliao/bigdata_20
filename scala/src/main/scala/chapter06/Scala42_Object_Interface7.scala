package chapter06

object Scala42_Object_Interface7 {


  def main(args: Array[String]): Unit = {

    // 特质混入时，初始化的时候顺序先父类、然后在当前类的多个特质从左到右一次初始化
    // 当执行特质的功能方法时，如果多个特质且有相同的方法，那么执行顺序从右到左
    // 特质中的super表示的含义不是父类的引用，而是表示上一个


    // 什么情况下考虑使用特质？
    // 使用特质主要考虑功能拓展

    //如果想改变特质的默认执行顺序，在super后添加中括号，可以指明执行的顺序

    // 特质在实现功能叠加是，super并不是上一级的概念，而是在编译时，会形成方法名称，实现功能调用

    // Java中super关键字也是一种引用，编译时会自动转换为应用的引用
    val mySql = new MySql()
    mySql.operDate()

  }

  trait Operate {
    def operDate(): Unit ={
      println("操作数据。。。")
    }



  }

//  trait DB extends Operate{
//    override def operDate(): Unit = {
//      print("向数据库")
//      super.operDate()
//    }
//  }

  trait DB extends Operate{
    override def operDate(): Unit = {
      print("向数据库")
      super[Operate].operDate()
    }
  }

  trait Log extends Operate{
    override def operDate(): Unit = {
      print("向日志文件")
      super.operDate()
    }
  }

//  class MySql extends DB with Log {
  class MySql extends Log with DB {  // 直接从DB跳过Log到Operate

  }
}

