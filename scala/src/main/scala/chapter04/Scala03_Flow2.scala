package chapter04

object Scala03_Flow2 {
  def main(args: Array[String]): Unit = {

    //Scala 中没有三元运算符
    // d = a ? b:c

    val age = 20

    val remark = if(age == 20) {
      "少年"
    }else{
      "青年"
    }
    println(remark)

    // 省略大括号模式
    val s = if(age == 20) "少年" else "青年"
    println(s)

  }

}
