package chapter07


object Scala15_Collection_Set {

  def main(args: Array[String]): Unit = {

    // TODO Scala 集合 - Set

    //java: Set 无序（插入的数据），不可重复

    // TODO 1.创建集合

    val set1: Set[Int] = Set(1,2,3,4,5,6,7,8)
    val set2: Set[Int] = Set(1,2,3,4,1,2,3,4)

    // TODO 2.操作数据
    val newSet1: Set[Int] = set1 + 9  // 默认不可变，产生了新的集合
    println(set1 eq newSet1)

    val newSet2: Set[Int] = set1 -1
    println(newSet2)

    val newSet3: Set[Int] = set1 ++ set2
    println(newSet3)


    // TODO 3.访问数据，数据遍历
    println(set1)
    println(set2)


  }

}
