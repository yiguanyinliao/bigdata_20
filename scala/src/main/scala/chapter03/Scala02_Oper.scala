package chapter03

object Scala02_Oper {

  def main(args: Array[String]): Unit = {
//    val s1 : String = "abc"
//    val s2 : String = "abc"
//
//    println(s1 == s2)
//    println(s1.equals(s2))


    val a = new String("abc")
    val b = new String("abc")

    println(a == b)  // Java中==是比较内存地址，编译阶段转为了equals
    println(a.equals(b))


    println(a eq(b))  // 比较内存地址的方法，在编译时转换为java的 ==，比较内存地址

  }

}
