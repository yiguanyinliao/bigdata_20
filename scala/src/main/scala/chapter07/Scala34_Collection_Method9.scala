package chapter07

object Scala34_Collection_Method9 {

  def main(args: Array[String]): Unit = {

    // TODO Scala - 集合 —— 计算函数

    val list = List(1, 2, 3, 4)
    // TODO reduce,reduceLeft,reduceRight都是集合内部数据的聚合
    //  但是在某些情况下，需要将集合之外的数据和集合内部的数据进行聚合
    //  所以reduce,reduceLeft,reduceRight这些方法不好用，可以采用折叠的方式来实现
    //  fold
    //  折叠的方法其实就是将集合外部的数据和集合内部的数据进行两两聚合
    //  所以集合之外的数据称之为zero(初始数)
    //  fold 方法的底层调的其实就是foldLeft
    //  foleLeft 其实初始值作为x使用，放置在集合的左边
    val result:Int = list.fold(10)(_+_)
    println(result)
    val result1:Int = list.fold(10)(_-_)
    println(result1)

    val result2 = list.foldLeft(10)(_-_)
    println(result2)

    // 1,2,3,4
    // 4,3,2,1.
    // TODO foleLeft 其实是将初始值作为y使用
    val result4 = list.foldRight(10)(_-_)
    println(result4)

  }
}
