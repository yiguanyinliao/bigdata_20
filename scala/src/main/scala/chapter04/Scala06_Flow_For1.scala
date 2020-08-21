package chapter04

object Scala06_Flow_For1 {

  def main(args: Array[String]): Unit = {

    val list = 1 to 5

    for (i:Int<-list) {
      println(i)
    }


    // 简化
    for(i <- 1 to 5) {     // 包含5
      println(i)
    }


    // 常见集合类
    for (i <- Range(1,5)) {  // 范围集合 不包含5

      println(i)
    }


    for (i<- 1 until 5) {   // 不包含5
      println(i)
    }

    for(i <- 1 to 5 by 2) {  // by 2 表示步长
      println(i)
    }

    for (i <- Range(1,5,2)) {
      println(i)
    }

    // 降序输出

    for (i <- 5 to 1 by -1) {
      println(i)
    }

    // 真实写法
    for (i <- 1.to(5)) {
      println(i)

    }



  }

}
