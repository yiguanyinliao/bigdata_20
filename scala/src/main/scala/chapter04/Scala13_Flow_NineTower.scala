package chapter04


object Scala13_Flow_NineTower {

  def main(args: Array[String]): Unit = {

    // TODO 九层妖塔

    val num = 9
    for (i <- 1 to 2 * num by 2; j = (2 * num - i) / 2) {
      println(" " * j + "*" * i + " " * j)
    }

  }

}
