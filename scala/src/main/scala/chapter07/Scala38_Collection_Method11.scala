package chapter07

import scala.collection.mutable

object Scala38_Collection_Method11 {

  def main(args: Array[String]): Unit = {

    // TODO Scala - 使用scala代码 将两个Map集合合并

    val A = mutable.Map("a"->1,"b"->2,"c"->3)
    val B = mutable.Map("a"->4,"b"->5,"c"->6)

    B.foreach(
      kv => {
        val key = kv._1
        val b_value = kv._2
        val a_value = A.getOrElse(key, 0)
        A.update(key, a_value + b_value)

      }
    )
    println(A)

  }
}
