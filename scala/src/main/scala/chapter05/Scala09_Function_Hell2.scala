package chapter05

object Scala09_Function_Hell2 {


  def main(args: Array[String]): Unit = {

    // TODO 2.可以将函数作为参数传递给函数

    def test(name:String):Unit={

      println("Name =" + name)
    }

    def test1(name:Int):Unit={

      println("Name =" + name)
    }
    test("zhagnsna")

    // TODO
//    def test2(f:(Int)=>Int):Unit={
    def test2(f:Int=>Int):Unit={
      // 如果参数是函数类型
      // 那么可以通过函数调用的方式来使用这个参数
      println(f(20))
    }

    def fun(i:Int):Int={
      i * 2
    }

//    val f = fun _
//    test2(f)
//    test2(fun _)
    test2(fun)



    // TODO 3.可以将函数作为返回值在函数值返回

  }

}
