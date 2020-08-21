package chapter07

import scala.collection.mutable.ArrayBuffer

object Scala06_Collection_Array5 {

  def main(args: Array[String]): Unit = {

    // TODO Scala 集合 - 可变数组

    // Scala 默认提供的集合都会不可变的。
    // 可变数组
    // TODO 1. 创建数组
    //  java: StringBuffer
    //  java 默认无参的构造方法
    //  scala的主构造方法就是类名后面加（）那个构造方法，跟java不一样
    //  默认初始化的容量是16

    // Scala学习集合的时候，一般不关心集合的内部处理结构，更多的关心怎么去用
    val array = new ArrayBuffer[String]()

    // TODO 2. 数据操作
    // 增加数据
    array.append("a")
    array.insert(0, "b")

    // 修改数据
//    array.update(1,"d")
    // 超出索引范围，会发生错误
    array(1) = "e"

    // 删除数据
    array.remove(1)
    // 删除数据时，超出索引范围，会发生错误
    array.remove(1,1)


    // TODO 3. 数据遍历
    //  如果是Array数组，打印是会产生hashCode的字符串，如果是ArrayBuffer，会将集合的数据直接打印出来
    println(array)


  }

}
