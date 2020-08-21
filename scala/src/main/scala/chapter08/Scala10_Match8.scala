package chapter08

object Scala10_Match8 {

  def main(args: Array[String]): Unit = {


    // TODO Scala - 模式匹配

    // （(姓名,鞋)，1）=> (姓名,(鞋,1))
    val list = List(
      (("zhangsan","鞋"),1),(("lisi","鞋"),2),(("wangwu","鞋"),3)
    )

    val list1: List[(String, (String, Int))] = list.map(
      t => {
        (t._1._1, (t._1._2, t._2))
      }
    )
    list1
    println(list1)

    val list2 = list.map {
      case ((name, item), count) => {
        (name, (item, count))
      }
    }
    println(list2)

    val list3 = List(
      ("a",1),("b",2),("c",3)
    )

    val list4 = list3.groupBy {
      case (word, count) => {
        word
      }
    }

    println(list4)

  }
}

