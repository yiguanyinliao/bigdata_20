package chapter08

object Scala12_Match10 {

  def main(args: Array[String]): Unit = {


    // TODO Scala - 模式匹配 - 偏函数

    // TODO 将该List(1,2,3,4,5,6,"test")中的Int类型的元素加一，并去掉字符串
    //  1.将集合中的Int数据加1
    //  2.将字符串过滤掉、


    // TODO map函数是全量函数，对集合中所有的数据都会进行操作，不能进行过滤
    //  偏函数在这里就可以使用。
    val list = List(1,2,3,4,5,6,"test")
    val newList = list.map(
      data => {
        data match {
          case i: Int => i + 1
          case _ => data  // 使用map方法这里必须得加上
          // case d => d
        }
      }
    )
    println(newList)
    val newList1 = newList.filter(data => {!data.isInstanceOf[String]})
    println(newList1)


    // TODO 采用偏函数方式实现需求
    //  collect() 采集方法 可以传递一个 偏函数 实现功能
    //  使用case操作的函数就是偏函数
    //  不是所有的函数都支持偏函数，如果函数的类型为PartialFunction，就是偏函数。
    val newList2 = list.collect {
      case i: Int => i + 1
    }
    println(newList2)

  }
}

