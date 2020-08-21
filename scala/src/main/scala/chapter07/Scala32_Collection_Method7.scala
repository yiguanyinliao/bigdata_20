package chapter07

object Scala32_Collection_Method7 {

  def main(args: Array[String]): Unit = {

    // TODO Scala - 集合 —— 计算函数

    val list = List(1,2,3,4)

    // TODO 需要对集合中的数据进行计算，但是计算逻辑不确定
    //  一般情况下，计算都应该产生计算结果
    //  List => map => Result (X)
    //  List => filter => Result (X)
    //  List(N) => 简化，规约（reduce） => Result (1)

    // TODO reduce方法中表示数据的处理规则
    //   为了使用方便，所有scala集合中数据处理基本上是两两操作
//    val result:Int = list.reduce((x:Int,y:Int) => {x*y})
//    val result:Int = list.reduce((x:Int,y:Int) => x*y)
//    val result:Int = list.reduce((x,y) => x*y)
    val result:Int = list.reduce(_*_)
    println(result)


  }
}
