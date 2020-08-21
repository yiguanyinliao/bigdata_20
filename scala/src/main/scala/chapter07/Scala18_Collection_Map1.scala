package chapter07

import scala.collection.mutable

object Scala18_Collection_Map1 {

  def main(args: Array[String]): Unit = {

    // TODO Scala 集合 - 映射 - Map - 可变

    val map = mutable.Map(
      "a" -> 1, "b"-> 2,"c"-> 3,"d"-> 4,"e" -> 1, "f"-> 2,"g"-> 3,"h"-> 4
    )

//    val map1 = Map(
//      "a" -> 1, "b"-> 2,"c"-> 3,"d"-> 4,"e" -> 1, "f"-> 2,"g"-> 3,"h"-> 4
//    )
//    println(map1)
    println(map)

    // put 可以增加数据，也可以修改数据
    map.put("d",10)
    map.put("w",10)
    map.update("a",11)  //更新
    println(map)
    map("a") = 20
    println(map)
    map.updated("b",20)  //updated 会产生新的map集合，需要重新赋值给一个map
    println(map)

    map.remove("b")
    println(map)
    map - "c"   // 会产生新的map ，使用-= 不会产生新的集合
    println(map)

    map.clear()  // 清除map里所以的数据
    println(map)


  }

}
