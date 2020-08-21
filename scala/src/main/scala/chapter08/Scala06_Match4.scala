package chapter08

object Scala06_Match4 {

  def main(args: Array[String]): Unit = {


    // TODO 模式匹配的关键字根据场景不同是可以省略的


    //    val t = (1, "zhagnsan", 30)
//    println(t._1)
//    println(t._2)
//    println(t._3)


    // TODO 匹配元祖

    val (id, name, age) = (1, "zhagnsan", 30)
    println(id)
    println(name)
    println(age)

//    val map = Map(
//      ("a",1),("b",2),("c",3)
//    )
//
//    for(kv <- map) {
//      println(kv._1 + "=" + kv._2)
//    }

    // TODO 匹配Map
    val map = Map(
      ("a",1),("b",2),("c",3)
    )

    for((k,v) <- map) {
      println(k + "=" + v)
    }

  }


}

