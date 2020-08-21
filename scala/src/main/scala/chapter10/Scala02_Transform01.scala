package chapter10

object Scala02_Transform01 {


  def main(args: Array[String]): Unit = {


    // TODO 隐式转换的规则需要提前声明，由编译器识别后使用
    //  在相同作用域范围内，相同的隐式转换规则不能多次出现
    //  一般将用于隐式转换的函数称之为隐式函数.
    implicit def transform(d:Double):Int ={
      d.toInt
    }


//    implicit def transform1(d:Double):Int ={  // 相同规则不能多次出现
//      d.toInt
//    }
//

    val i: Int = 5.0
    println(i)



  }
}
