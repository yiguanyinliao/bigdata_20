package chapter07

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

object Scala08_Collection_Array7 {

  def main(args: Array[String]): Unit = {

    // TODO Scala 集合 - 可变数组 和 不可变数组的转换
    //

    val array = Array(1,2,3,4)
    val buffer = ArrayBuffer(1,2,3,4)

    // 不可变数组 => 可变
    val buffer1: mutable.Buffer[Int] = array.toBuffer

    // 可变数组 => 不可变数组
    val array2: Array[Int] = buffer1.toArray


  }

}
