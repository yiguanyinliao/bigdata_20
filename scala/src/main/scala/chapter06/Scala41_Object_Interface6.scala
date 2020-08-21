package chapter06

object Scala41_Object_Interface6 {


  def main(args: Array[String]): Unit = {

    // 如果将特质看作抽象类，那么就存在类的初始化操作
    // 特质的初始化顺序
    // 1. 父类先执行初始化
    // 2. 多个特质按照从左到右的顺序完成初始化
    // 3. 当前类的初始化
    new MyClass()

  }

  trait Mytrait1{
    println("Mytrait1")

  }

  trait Mytrait2 extends Mytrait1 {
    println("Mytrait2")

  }

  trait Mytrait3 extends Mytrait1{
    println("Mytrait3")

  }

  class MyParent extends Mytrait1{
    println("MyParent")

  }

  class MyClass extends MyParent with Mytrait2 with Mytrait3{
    println("MyClass")

  }

}

