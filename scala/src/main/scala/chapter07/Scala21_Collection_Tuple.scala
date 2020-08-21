package chapter07


object Scala21_Collection_Tuple {

  def main(args: Array[String]): Unit = {

    // TODO Scala 集合 - Tuple 元祖
    //  元祖（tuple),使用小括号包含即可
    //  元素的组合
    //  1, zhangsan, 30
    val t = (1, "zhangsan", 30,true)

//    val t1 = (1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4)
    // TODO 访问数据
    //  如果想要获取某个数据，根据元素的下标访问
    //  元祖中元素的个数最多有22个
    //  元祖中的22个数据不限定类型，所以可以放很多
    println(t._1)
    println(t._2)
    println(t._3)
    println(t._4)

    // TODO 遍历数据
    //  注意: 直接遍历会报错, 因为Scala的元组是一个整体, 需要利用迭代器
    //  Tuple 不像List一样有map、flatMap 等方法直接操作元素，只能通过混入的ProductN（n代表1-22的数字）
    //  的productIterator函数生成一个Iterator来操作数据，并且productIterator的返回类型是Iterator[Any] ，
    //  类型参数是Any ，所以操作数据的时候还要进行类型转换。比如_.asInstanceOf[Int] 或者模式匹配.
    val iterator: Iterator[Any] = t.productIterator
    while (iterator.hasNext) {
      println(iterator.next())
    }

    val m = Map(2->(3,2) , 1->(2,1,3))
//    val m2=m.mapValues(_.sum)  (X)
        val m2=m.mapValues(_.productIterator.map(_.asInstanceOf[Int]).sum)
    println(m2)


    // 索引定位
    println(t.productElement(0))

    // TODO 元祖中的数据不允许修改


  }

//  class User{
//    var id : Int = _
//    var nmae : String = _
//    var age : Int = _
//  }

}
