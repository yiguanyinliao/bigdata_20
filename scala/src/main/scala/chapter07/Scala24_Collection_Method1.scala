package chapter07

object Scala24_Collection_Method1 {

  def main(args: Array[String]): Unit = {

    // TODO Scala 集合 - 常用方法
    val list = List(1,2,3,4)
    //    val list = List(1)
    val list1 = List(3,4,5,6)
    val list2 = List("a","b","c","d","e")



    // TODO 获取集合的一部分数据
    //  对于我们的集合来讲，除了头就是尾
    //  递归是函数式编程中用的最多的算法
    println("获取集合第一个数据：" + list.head)
    println("获取集合尾部数据：" + list.tail)   // 针对head来讲
    println("获取集合最后一个数据：" + list.last)
    println("获取集合初始数据：" + list.init)  // 针对last来讲
    println("=======：" + list.tail.tail.tail)
    println("(迭代)：" + list.tails)
    println("(迭代)：" + list.inits)


    // TODO 集合之间的关联
    // 交集
    println(list.intersect(list1))
    // 并集
    println(list.union(list1))
    // 差集
    println(list.diff(list1))
    println(list1.diff(list))
    // 切分集合
    println(list.splitAt(2))
    println(list.splitAt(1))
    println(list2.splitAt(3))
    // 滑动，将集合的一部分数据当成一个整体来使用
    // 讲一个集合的一部分作为整体，可以称为窗口
    // 默认情况下，滑动的幅度为1，可以改变
    val list4 = List(1,2,3,4,5,6,7,8,9)
//    val iterator: Iterator[List[Int]] = list4.sliding(3)
    val iterator: Iterator[List[Int]] = list4.sliding(3,2)
    println("=======================")
    while(iterator.hasNext){
      val value = iterator.next()
      println(value)
    }
    println("=======================")

    // 拉链  将两个集合相同位置的数据放在一起，如果两个集合的数量不一致，多余的数据无法关联
    val list5 = List(1,2,3,4,9)
    val list6 = List(5,6,7,8)
    val tuples: List[(Int, Int)] = list5.zip(list6)
    println(tuples)

    println(list5.zipWithIndex)  //数据和索引的拉链

  }

}
