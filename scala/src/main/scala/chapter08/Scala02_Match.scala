package chapter08

object Scala02_Match {

  def main(args: Array[String]): Unit = {


    // TODO Scala 中的模式匹配类似于Java中的switch语法
    //  Scala中采用match进行匹配
    //  1.使用 => 代替冒号
    //  2.满足条件处理中不需要使用break，会自动跳出
    //  3.如果没有一个case成成立，需要设置默认值(下划线)
    //    这里的下划线表示任意值
    //  4.匹配顺序为从上到下
    //  5.如果match没有匹配上，会发生错误
    //    所以推荐使用 _ 来进行默认处理，最好放在最后一行
    //  6.如果把任意值放在最前面，后面的匹配不会进行，会自动跳出、


    val i: Int = 5

    i match {

//      case _ => {
//        println("任意值")  // 如果把任意值放在最前面，后面的匹配不会进行，会自动跳出
//      }
      //      case 5=>println("i=5")

      case 5 => {
        println("i=5")
      }
      case 10 => {
        println("i=10")
      }
      case _ => {
        println("任意值")
      }

    }
    println("main.........")
  }

}
