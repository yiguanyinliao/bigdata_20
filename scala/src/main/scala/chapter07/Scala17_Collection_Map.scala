package chapter07

object Scala17_Collection_Map {

  def main(args: Array[String]): Unit = {

    // TODO Scala 集合 - 映射 - Map
    // java map => 无序，存储的是 K-V 键值对对象，Key不能重复， V可以重复
    // scala map => 无序，存储的是 K-V 键值对对象，Key不能重复， V可以重复
    // k -v 键值对对象
    // K => V (关联，映射)
    // Scala中的键值对， 看-> v

    val map = Map(
      "a" -> 1, "b"-> 2,"c"-> 3,"d"-> 7
    )

    val map1 = Map(
      "a" -> 4, "b"-> 5,"c"-> 6
    )

    // 相同的key会做更新操作
    val newMap: Map[String, Int] = map + ("d"-> 4)
    println(map)
    println(newMap)

    val newMap1: Map[String, Int] = map - "a"
    println(newMap1)

    val newMap2: Map[String, Int] = map ++ map1  // key 不能重复，相同的key会覆盖
    println(newMap2)

    val newMap3: Map[String, Int] = map.updated("a",10)  // 不可变会产生新的Map
    println(newMap3)

  }

}
