package chapter04

object Scala08_Flow_For3 {

  def main(args: Array[String]): Unit = {


    // 简化嵌套
    for ( i <- Range(1,5); j <- Range(1,4) ) {
      println("i = " + i + ",j = " + j )
    }


    // 普通嵌套
    for ( i <- Range(1,5) ) {
      for ( j <- Range(1,4) ) {
        println("i = " + i + ",j = " + j )
      }
    }


    for (i <- 1 to 5) {
      val j = i-1
      println(j)

    }

    for (i <- 1 to 5; j = i-1) {
      println(j)
    }


  }

}
