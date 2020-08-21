package chapter07

object Scala36_Collection_Method10 {

  def main(args: Array[String]): Unit = {

    // TODO Scala - 集合 —— 计算函数

    val list = List(1,2,3,4)

    // TODO Scala scan方法和fole方法很相似
    //  fold 方法会将集合最终聚合成一个结果
    val i: Int = list.fold(0)(_+_)
    println(i)
    // TODO scan方法会将每一次的计算结果保存下来，形成计算结果的集合.
    val ints:List[Int] = list.scan(0)(_+_)
    println(ints)

    val ints1:List[Int] = list.scanLeft(0)(_-_)
    println(ints1)
    val ints3: List[Int] = list.scanRight(0)(_-_)
    println(ints3)


  }
}
