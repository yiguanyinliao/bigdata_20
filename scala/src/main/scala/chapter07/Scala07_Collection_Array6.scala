package chapter07

import scala.collection.mutable.ArrayBuffer

object Scala07_Collection_Array6 {

  def main(args: Array[String]): Unit = {

    // TODO Scala 集合 - 可变数组

    // Scala 中可变集合，使用的方法最好是单词方法
    // Scala 中不可变集合，使用的方法时颜文字方法
    val array = ArrayBuffer(1,2,3,4)
    val array2 = ArrayBuffer(5,6,7,8)
//    val array = ArrayBuffer()
    val array1:ArrayBuffer[Int] = array += 5  // 产生元素增加，内存不变的数组


    val array3: ArrayBuffer[Int] = array ++ array2  // 两个数组合并，产生了新的数组
    val array4: ArrayBuffer[Int] = array ++= array2  // 两个数组合并，没有产生新的数组
    println(array3)
    println(array4)

    println(array eq array1)
    println(array eq array3)
    println(array eq array4)



  }

}
