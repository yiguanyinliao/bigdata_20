package chapter07

object Scala10_Collection_seq1 {

  def main(args: Array[String]): Unit = {

    // TODO Scala 集合 - seq 序列

    // 空集合,一般产生新的集合

    val nil = Nil
    println(nil)  // 打印结果为List()

    // 产生新的集合
    val list: List[Int] = 1::2::3::Nil
    println(list)


    val list1 = List(1, 2)
    // 当前场合将List集合作为一个整体放倒Nil中
    val list2: List[Any] = 3::4::list1::Nil
    // 如果想要List集合中的每一个元素拆分开放倒Nil中，需要特殊处理，叫做扁平化
    // 扁平化：讲一个整体拆成一个个的元素使用
    val list3: List[Int] = 3::4::list1:::Nil // 扁平化处理

    println(list2)
    println(list3)
  }

}
