package chapter08

object Scala09_Match7 {

  def main(args: Array[String]): Unit = {


    // TODO Scala - 模式匹配

    val Array(first, second, _*) = Array(1, 7, 2, 9)
    println(s"first=$first,second=$second")

    val Person(name, age) = Person("zhangsan", 16)
    println(s"name=$name,age=$age")


    val map = Map("A" -> 1, "B" -> 0, "C" -> 3)
    for ((k, v) <- map) { //直接将map中的k-v遍历出来
      println(k + " -> " + v) //3个
    }

    for ((k, _) <- map) { // 不关心v的值，可以使用下划线代替
      println(k)
    }

    for ((k, 0) <- map) { // 如果v不是0,过滤
      println(k+ " -> " + 0)  // B->0
    }

    //if v == 0 是一个过滤的条件
    for ((k, v) <- map if v >= 1) {
      println(k + " ---> " + v) // A->1 和 c->33
    }

    val list = List(("a",1),("b",2),("c",3))
//    println(list.map(
//      kv => {
//        (kv._1, kv._2 * 2)
//      }
//    ))
    // TODO 如果你们函数的参数进行模式匹配，那么不能省略case关键字
    //  如果使用模式匹配时，需要把()改成{}、
    list.map{
      case (k,v) => {
        (k,v*2)
      }
    }

    val newList = list.map {      // TODO map 方法的()改成大括号{}
      case (word, count) => {
        (word, count * 2)
      }
    }
    println(newList)



  }
  case class Person(name: String, age: Int)
}

