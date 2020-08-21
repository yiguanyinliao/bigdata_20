package chapter07

object Scala22_Collection_Tuple1 {

  def main(args: Array[String]): Unit = {

    // TODO Scala 集合 - Tuple 元祖

    // 如果元祖中的元素只有两个，那么称为对偶元祖，也称为键值对对象
    //    val tuple = ("a", 1)
    val tuple: Tuple2[String, Int] = ("a", 1)
    val tuple1: (String, Int) = ("a", 1)
    println(tuple._1)
    val map = Map(("a", 1), ("b", 2), ("c", 3)) // tuple 可以当做键值对对象来用
    println(map)

    // -> 其实是一个方法，回返回Tuple
//    "a"-> 1

    for (kv <- map) {
      println(kv._1 + "=" + kv._2)
    }

  }

}
