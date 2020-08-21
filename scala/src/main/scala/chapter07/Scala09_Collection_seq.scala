package chapter07

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

object Scala09_Collection_seq {

  def main(args: Array[String]): Unit = {

    // TODO Scala 集合 - seq 序列
    // java: List => 有序（插入数据的顺序）可重复
    // scala:Seq => 有序（插入数据的顺序）可重复

    // TODO 1.创建集合
    // List 集合是一个抽象类，不能直接构建对象，需要采用apply方法
    val list = List(1,2,3,4)
    val list1 = List(1,2,3,4)

    // TODO 2.数据操作
    val newList: List[Int] = list :+ 5
    println(newList eq list)

    val newList1: List[Int] = 6 +: list

    val newList2: List[Int] = list ++ list1

    // TODO 3.数据的访问和遍历
    println(list)
    println(newList)
    println(newList1)
    println(newList2)


  }

}
