package chapter07

object Scala33_Collection_Method8 {

  def main(args: Array[String]): Unit = {

    // TODO Scala - 集合 —— 计算函数


    // TODO reduce 方法要求计算时参数类型和结果类型一致
    //  reduce 方法的底层其实是reduceLeft
    val list = List(1, 2, 3, 4, 5)
    // (A1,A1) => A1
    val result = list.reduce(_ + _)
    println(result)
    // TODO reduceLeft 方法要求第一个参数和结果的参数类型保持一致，第二个参数就是当前集合数据的类型
    //  第一个参数类型和第二个参数类型之间有关系
    // [B>:Int]
    // (B,Int)=> B
    // TODO 当处理数据类型一致时，reduce可以代替reduceLeft
    val result1: Int = list.reduceLeft(_ + _)
    println(result1)

    val result11 = list.reduceLeft(_ - _)
    println(result11)


    // TODO reduceRight 是从右边开始两两做计算
    // TODO reduceLeft 是从左边开始两两做计算
    // TODO reduceLeft传参y其实是需要进行处理的数据，x其实是计算结果
    //      reduceRight 传参x其实是需要进行处理的数据，y其实是计算结果
    //(Int,B) => B
    val result2: Int = list.reduceRight(_ + _)
    println(result2)

    // 1,2,3,4,5
    //TODO reduceRight 实现原理：
    // 1.集合数据反转
    //    5,4,3,2,1
    // 2.调用reduceLeft
    //    5,4
    // 3.两两计算时，会交换参数的顺序
    //    5,4 => 4,5 => -1
    //    -1.3 => 3,-1 => 4
    //    4,2 => 2,4 => -2
    //    -2,1 => 1,-2 => 3

    val result22: Int = list.reduceRight(_ - _)
    println(result22) // 结果是3  传参的顺序会影响结果

    // TODO 如何分析reduceLeft和reduceRight的计算结果？
    //  采用加括号的方式就可以
    //  reduceLeft：从左边两两加括号
    //  ((((1，2)，3)，4)，5)  => _-_ => -13
    //  reduceRighr：从右边两两加括号
    //  (1，(2，(3，(4，5)))) => _-_ => 3

  }
}
