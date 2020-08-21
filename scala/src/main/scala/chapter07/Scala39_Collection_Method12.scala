package chapter07

import scala.collection.mutable

object Scala39_Collection_Method12 {

  def main(args: Array[String]): Unit = {

    // TODO Scala - 使用scala代码 将两个Map集合合并

    val A = mutable.Map("a" -> 1, "b" -> 2, "c" -> 3)
    val B = mutable.Map("a" -> 4, "b" -> 5, "c" -> 6)

    val A1 = 0
    val B1 = List(1, 2, 3, 4)

    // TODO fold 方法要求初始值的数据类型和集合中的数据类型要保持一致
    val result: Int = B1.fold(A1)(_+_)
    println(result)

    // TODO foldLeft方法的初始值的数据类型和集合中的数据类型可以不一致
    //  最终的计算结果数据类型应该和初始值保持一致
    val result2: Int = B1.foldLeft(A1)(_+_)


    val newMap: mutable.Map[String, Int] = B.foldLeft(A)(
      (map, kv) => {

        val k = kv._1
        val v = kv._2

        val v1 = map.getOrElse(k, 0)
        map.update(k, v + v1)

        map  // 最后一行表示返回的数据
      }
    )

    println(newMap)
  }
}
