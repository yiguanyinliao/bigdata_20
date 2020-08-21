package chapter07

import scala.collection.mutable

object Scala19_Collection_Map2 {

  def main(args: Array[String]): Unit = {

    // TODO Scala 集合 - 映射 - Map - 可变

    val map = mutable.Map(
      "a" -> 1, "b"-> 2,"c"-> 3,"d"-> 4,"e" -> 1, "f"-> 2,"g"-> 3,"h"-> 4
    )


    // TODO 获取数据
    //  从Map中更加key获取数据，value 可能存在，也可能不存在
    //  如果取到数据，直接进行操作，如果取不到，获取的结果为null，有可能出现空指针异常
    //  Scala 中的Map为了避免出现空指针异常，是用了一种特殊的类型：选项类型Option（有或者没有）
    //  Option只有两个类型：Some和None
    //  当能够获取到值的时候，就是Some
    //  如果取不到值的时候，就是None
    val value = map.get("a")  // Some(1)
    val value1 = map.get("i")  // None

    // TODO Option类型的get方法只有在Some对象时候使用
    println(value.get)
    // TODO 如果是None会出现异常
    if(!value1.isEmpty) {
      println(value1.get)
    }
    // TODO 如果从Map中获取数据获取不到，需要设定默认值时，scala提供了特殊的方法使用
    val i = map.getOrElse("i",0)
    println(i)
  }

}
