package chapter04

object Scala09_Flow_For4 {


  def main(args: Array[String]): Unit = {


    // for 循环表达式结果就是unit，要获取结果需要使用yield
    var result = for (i <- 1 to 5) yield{
      i * 2
    }
    // 返回向量集合 Vector
      println(result)

  }

}
