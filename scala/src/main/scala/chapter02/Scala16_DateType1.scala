package chapter02

object Scala16_DateType1 {
  def main(args: Array[String]): Unit = {
    //在java中null表示变量为空，是个关键字
    // 在scala中表示一个类型为Null的对象
    val name = null
    println(name)

    // Nothing : 无值，可以在很多地方使用
    // Unit:没有返回值，一般用于方法上面
  def test():Nothing = {
      throw new Exception
    }
  }
}
