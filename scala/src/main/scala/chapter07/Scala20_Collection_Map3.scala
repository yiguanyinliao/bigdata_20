package chapter07

import scala.collection.mutable

object Scala20_Collection_Map3 {

  def main(args: Array[String]): Unit = {

    // TODO Scala 集合 - 映射 - Map - 可变

    val map = mutable.Map(
      "a" -> 1, "b"-> 2,"c"-> 3,"d"-> 4,"e" -> 1, "f"-> 2,"g"-> 3,"h"-> 4
    )

    // TODO 遍历数据
    val keys: Iterable[String] = map.keys
    val values: Iterable[Int] = map.values
    val set: collection.Set[String] = map.keySet
    val iterator: Iterator[String] = map.keysIterator
    val iterator1: Iterator[Int] = map.valuesIterator
  }

}
