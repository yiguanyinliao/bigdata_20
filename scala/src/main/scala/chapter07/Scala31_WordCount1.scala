package chapter07

object Scala31_WordCount1 {

  def main(args: Array[String]): Unit = {

    // TODO Scala 小练习 - WordCount的简化版

    val list= List(
      "Hello Sacla Spark Flink",
      "Hello Sacla Spark",
      "Hello Sacla",
      "Hello"
    )

    val wordCountMap: Map[String,Int] = list.flatMap(_.split(" "))
      // .groupBy(_)  // TODO 这里不能使用_,是因为没有使用word参数，直接返回了
      .groupBy(word => word)
      .map(
        kv => {
          (kv._1, kv._2.size)
        }
      )
    wordCountMap.toList.sortBy(kv=>kv._2)(Ordering.Int.reverse).take(3).foreach(println)



//    val list : List[String] = List(
//      "Hello Scala Spark Flink",
//      "Hello Scala Spark",
//      "Hello Scala",
//      "Hello"
//    )
//
//    list
//      .flatMap(_.split(" "))
//      .groupBy(word=>word)
//      .map(
//        kv => {
//          (kv._1, kv._2.size)
//        }
//      )
//      .toList
//      .sortBy(_._2)(Ordering.Int.reverse)
//      .take(3)
//      .foreach(println)



    // TODO 下划线使用的问题
    //  1.在匿名函数中，下划线主要用于简化函数参数的使用
    //  2.在你们函数中，如果函数的参数只使用了一次，可以使用下划线来代替参数
    //  3.下划线可以将函数作为整体来使用，所以在使用过程中，如果函数传递的参数不做处理，直接
    //    返回，不能使用下划线_代替
    //  4.下划线代替参数，严格按照参数的顺序使用
    //  5.下划线的逻辑操作不要放在内部函数的传参上
    //  6.下划线的简化操作主要使用简单逻辑，如果复杂逻辑不要简化
    val list1 = List(1,2,3,4)
    list1.foreach((num:Int)=> {println(num)})
    list1.foreach(num=> println(num))
    list1.foreach(println(_))   // TODO 使用到了 num


    //    list1.foreach(println(_ + 123)) //  下划线的逻辑操作不要放在内部函数的传参上
    list1.foreach(_ + 123)

  }
}
