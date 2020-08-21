package chapter07

object Scala11_Collection_seq2 {

  def main(args: Array[String]): Unit = {

    // TODO Scala 集合 - seq 序列

    val list1 = List(1,2,3,4)
    val list2 = List(5,6,7,8)
    val newList: List[Int] = List.concat(list1,list2)
    println(newList)

    val newList2: List[String] = List.fill[String](3)("a")
    println(newList2)

  }

}
