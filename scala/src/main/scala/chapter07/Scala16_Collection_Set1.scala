package chapter07

import scala.collection.mutable

object Scala16_Collection_Set1 {

  def main(args: Array[String]): Unit = {

    // TODO Scala 集合 - Set -可变

    val set = Set()   // 不可变集合
    val set1 = mutable.Set(1,2,3,4)  // 可变Set集合

    set1.add(5)  // 增加元素，因为无序所以没有append和insert
    set1.update(6,true)  // update 用于更新整个集合，当时true的时候，就把6包含到集合里
    set1.update(3,false)  // false 是不把3包含到集合里面  HashSet(1, 2, 4, 5, 6)

    set1.remove(2)   // 删除元素2

    println(set1)
    println(set1.mkString(","))

  }

}
