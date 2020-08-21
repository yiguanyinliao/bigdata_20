package chapter07

object Scala23_Collection_Method {

  def main(args: Array[String]): Unit = {

    // TODO Scala 集合 - 常用方法
    val list = List(1,2,3,4,4)

    // TODO 获取集合的长度
    println(list.length)
    println(list.size)    // size 最终走的是length
    println(list.isEmpty)

    // TODO 集合的遍历
    println(list.iterator)
    println(list.mkString(","))
    list.foreach(println)

    // TODO 集合数据的判断和获取
    println(list.contains(1))
    println(list.take(2))     // 获取数据
    println(list.take(3))     // 获取数据
    println(list.takeRight(2))         // 从右边开始获取
    println(list.drop(2))     // 丢弃前两个
    println(list.dropRight(2))        // 丢弃后面两个

    // TODO 集合的操作
    println(list.reverse)    // 结合的反转
    println(list.distinct)   // 去重




  }

}
