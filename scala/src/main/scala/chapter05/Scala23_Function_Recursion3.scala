package chapter05

object Scala23_Function_Recursion3 {

  def main(args: Array[String]): Unit = {

    // TODO Scala 函数 -递归

    // 尾递归： 压栈的同时，需要将当前方法弹栈，不需要等待
    // 将计算结果做为函数参数的一部分，而不是函数的一部分

    // 5 => 5*1
    // 4 => 5*4*1
    // 3 => 5*4*3*1
    // 2 => 5*4*3*2*1
    // 1 => 5*4*3*2*1

    // TODO Java 中有尾递归，但是不会优化成while
    //  但是会出现栈溢出
    //  Scala中有尾递归，会优化成while循环，不会出现栈溢出

    def test(num: Int,result:Int): Int = {
      if (num <= 1) {
        result
      } else {
//        num * test(num - 1)

        test(num - 1,num*result)   // result 初始值为1，再为5*1，再为5*4*1 。。。。。
      }
    }

    println(test(5,1))

  }
}