package chapter08

object Scala03_Match1 {

  def main(args: Array[String]): Unit = {


    // TODO Scala 中的模式匹配类似于Java中的switch语法

    // TODO 模式匹配是可以有返回结果的

        var a: Int = 10
        var b: Int = 20
        var c: Char = 'd'
        var result = c match {
          case '+' => a + b
          case '-' => a - b
          case '*' => a * b
          case '/' => a / b
          case _ => "illegal"
        }
        println(result)

//    var a: Int = 10
//    var b: Int = 20
//    var c: Char = 'd'
//    var result:Any = {
//      c match {
//        case '+' => a + b
//        case '-' => a - b
//        case '*' => a * b
//        case '/' => a / b
//        case _ => "illegal"
//      }
//    }
//    println(result)
  }

}
