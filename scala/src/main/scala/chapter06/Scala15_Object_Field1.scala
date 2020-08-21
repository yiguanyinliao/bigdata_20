package chapter06

object Scala15_Object_Field1 {


  def main(args: Array[String]): Unit = {

    // TODO Scala 面向对象 - 属性
    //  访问权限：方法的提供者和方法的调用者之间的关系
    //  Java:
    //    1. 私有的：private    => 同类
    //    2. 包 ：default      => 同类、同包
    //    3. 受保护的：protected  => 同类、同包、子类
    //    4. 公共的：public  => 任意地方
    //  Scala:
    //    1. 私有的：private    => 同类
    //    2. 包 ：private[包名]  => 同类、指定包
    //            这里的包名可以不是固定的，但是需要和当前的包路径有关系
    //    3. 受保护的：protected  => 同类、子类
    //    4. 公共的：（default）  => 任意地方

    val user = new User14()
    user.name_package
    user.name_public


  }


}


class ChildUser14 extends User14 {
  def test(): Unit = {
    println(name_public)
    println(name_package)
    println(name_protected)
  }
}

class User14 {

  private var name_private: String = _
  private[chapter06] var name_package: String = _
  protected var name_protected: String = _
  var name_public: String = _
}