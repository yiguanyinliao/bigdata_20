package chapter05

object Scala10_Function_Hell3 {


  def main(args: Array[String]): Unit = {

    def test(f:(Int,Int)=> Int):Int = {
      f(10,20)
    }


    def fun(i: Int,j: Int):Int ={
      i + j
    }

    println("========")
    println(test(fun))

    // 冗余
    val result = test(fun)
    println(result)

    // TODO 将函数作为参数传递给另外一个函数使用
    //   一般情况下，不会独立声明这个函数，二是使用匿名函数
    //  匿名函数： (i:Int, j:Int):Int => {函数体}
    val result1 = test((i:Int,j:Int)=> {i + j})
    val result2 = test((i:Int,j:Int)=> {i*j})
    println(result1)
    println(result2)

    // TODO 匿名函数也可以遵循至简原则
    //  如果，函数逻辑代码只有一行，省略花括号
    val result3 = test((i:Int,j:Int)=> i + j)
    // TODO 可以推断出类型，省略类型
    val result4 = test((i,j)=> i + j)

    // TODO 如果参数在逻辑上只使用了一次，参数可使用 _ 代替，参数可以省略
    //  下划线是按照参数的声明顺序进行代替的，第一个下划线就代替第一个参数
    val result5 = test(_ + _)

    println(result5)



  }

}
