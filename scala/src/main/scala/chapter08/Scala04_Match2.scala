package chapter08

object Scala04_Match2 {

  def main(args: Array[String]): Unit = {


    // TODO 模式匹配是有匹配规则的

    // TODO 1.匹配常量
    def describe(x: Any) = x match {
      case 5 => "Int five"
      case "hello" => "String hello"
      case true => "Boolean true"
      case '+' => "Char +"
    }

    println(describe(5))
    println(describe("hello"))
    println(describe(true))
    println(describe('+'))
//    println(describe(1))

    println("="*57)
    // TODO 2.匹配类型
    //  当我们的数据类型不确定时，可以通过类型匹配进行操作
    //  如果类型匹配成功，那么类型前面的变量用于相应类型的操作
    //  如果所有的类型不匹配，会执行最后的下划线的case
    //  如果使用下划线，那么无法使用变量名，所以下划线可以使用变量名代替
    //  匹配类型的场合下，不考虑泛型,除了数组Array（但数组里的不是泛型！！！！！）
    //  Array[Int] 这里的Int不是泛型
    //  Scala Array[Int] => Java int[]
    //  Scala List[Int] => Java List<Interger>
    //  、
    def describe1(x: Any) = x match {
      case i: Int => "Int"
      case s: String => "String hello"
//      case m: List[_] => "List"      // 在泛型中 _ 代表所以类型
      case m: List[String] => "List"
      case c: Array[Int] => "Array[Int]"
      case someThing => "something else " + someThing
    }

    println(describe1(1))
    println(describe1("Scala"))
    println(describe1(List("a","b")))
    println(describe1(Array(1,2,3,4)))
    println(describe1(Array("1","2","3","4")))
    println(describe1(List(1,2,3,4)))
    println(describe1(new User))
    println(describe1())

  }


class User{

}

}

