package chapter06

object Scala36_Object_Interface1 {


  def main(args: Array[String]): Unit = {

    // TODO Scala 面向对象 - 接口（特质）
    //  Scala 中没有接口的概念，也就没有interface关键字
    //  可以将多个类中相同的特征从类里剥离出来，形成特殊的语法结构
    //  这个结构称之为特质
    //  如果一个类符合这个特质，就可以将这个特质混入到类中

    // TODO 特质
    // trait 特质名称
    // 使用特质

    val t: MyTrait = new Person()
    t.run()


  }

}

// 声明特质
trait MyTrait{
  // 通用的特征
  def run():Unit
}

// 将特质混入到类中
class Person extends MyTrait{
  override def run(): Unit = {
    println("run.....")
  }
}