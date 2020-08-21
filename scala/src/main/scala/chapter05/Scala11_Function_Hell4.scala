package chapter05

object Scala11_Function_Hell4 {


  def main(args: Array[String]): Unit = {

//    def test(f: (String) => Unit): Unit = {
    def test(f: String => Unit): Unit = {
      f("zhangsan")
    }

    def fun(s: String): Unit = {
      println("name = " + s)
    }

    test((s: String) => {println(s)})
    test((s: String) => println(s))
    test((s) => println(s))
    // TODO 如果匿名函数参数列表只有一个参数，那么可以省略小括号
    test(s => println(s))

    test(println(_))
    // 至简最终版本
    test(println)


  }

}
