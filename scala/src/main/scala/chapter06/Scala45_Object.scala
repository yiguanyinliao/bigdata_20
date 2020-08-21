package chapter06

object Scala45_Object {

  println("1111")
  def main(args: Array[String]): Unit = {

    // TODO 1.当前的main是静态方法吗？
    //        不是，是伴生对象的成员方法
    //  2.Scala45是什么？
    //        对象
    //  3.Scala45中的大括号的内容代表什么意思？
    //        伴生对象的类主题内容，所以在构建对象时，会完成类的初始化操作


    // TODO 类初始化(1111,333) => 对象 => main()(成员方法)(222)

    println("222")


  }
  println("333")

}

