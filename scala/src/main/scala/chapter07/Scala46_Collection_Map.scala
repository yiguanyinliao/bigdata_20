package chapter07

object Scala46_Collection_Map {

  def main(args: Array[String]): Unit = {

    // TODO Scala - 集合 - Map

    val list = List(("a",1),("b",2),("c",3))
    val map = Map(("a",1),("b",2),("c",3))

    // TODO 函数的声明包含：
    //  函数名（输入）：输出
    //  map中传递的匿名函数没有名称，关心的就只有输入和输出
    //  (t) => B
    //    输入：Tuple
    //    输出：B。

    list.map(
      t => (t._1,t._2*2)
    )


    list.map(
      (t) => (t._1,t._2*2)
    )

    list.map(
      (t) => {(t._1,t._2*2)}
    )

  }
}
