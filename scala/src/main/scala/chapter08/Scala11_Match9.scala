package chapter08

object Scala11_Match9 {

  def main(args: Array[String]): Unit = {


    // TODO Scala - 模式匹配 - 偏函数
    //  以偏概全
    //  偏 => 部分
    //  全 => 整体
    //  所谓的偏函数其实就是对满足条件的一部分数据进行处理的函数
    //  这里的全表示对所以的数据进行处理、

    // TODO 将该List(1,2,3,4,5,6,"test")中的Int类型的元素加一，并去掉字符串
    //  1.将集合中的Int数据加1
    //  2.将字符串过滤掉、

    val list = List(1,2,3,4,5,6,"test")
//    list.map(         // (X)
//      data => {
//        data + 1
//      }
//    ).filter(data=>{!data.isInstanceOf[String]})

    val newList = list.filter(data => {
      !data.isInstanceOf[String]
    }).map(
      data => {
        data.asInstanceOf[Int] + 1
      }
    )
    println(newList)

  }
}

