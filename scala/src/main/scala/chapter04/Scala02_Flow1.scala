package chapter04

object Scala02_Flow1 {

  def main(args: Array[String]): Unit = {


    // scala 中表达式有返回值
    // 表达式中返回值是表达式中满足条件的最后一行代码的执行结果
    // 表达式不是真正的方法，不需要return
    val age = 20
    val result = if (age == 20) {
//      println("年龄为20")
        "年龄为20"
    }
    println(result)
  }

}
