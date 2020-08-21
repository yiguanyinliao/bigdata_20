package chapter07

object Scala41Collection_Par {

  def main(args: Array[String]): Unit = {

    // TODO Scala - 集合 - 并行

    // TODO 没有使用par是串行的效果
    val result1 = (0 to 100).map {
      num => {
        Thread.currentThread.getName
      }
    }

    // TODO 使用par使用多线程，有并行的效果，使用了多核处理
    val result2 = (0 to 100).par.map {
      x => {
        Thread.currentThread.getName
      }
    }

    println(result1)
    println(result2)

  }
}
