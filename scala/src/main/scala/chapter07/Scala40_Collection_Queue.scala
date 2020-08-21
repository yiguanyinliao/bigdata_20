package chapter07

import scala.collection.mutable

object Scala40_Collection_Queue {

  def main(args: Array[String]): Unit = {

    // TODO Scala - 集合 - Queue 队列

    val que = new mutable.Queue[String]()

    // 添加元素
    que.enqueue("a","b","c")

    // 获取数据
    println(que.dequeue())
    println(que.dequeue())
    println(que.dequeue())

    // TODO Kfaka: 如何保证数据的有序性
    //  Kafka无法保证分区间数据有序性，但是同一分区内数据是有序的
    //  producer 使用双端队列保证发的数据是有效的，发送的数据在同一分区内。




  }
}
