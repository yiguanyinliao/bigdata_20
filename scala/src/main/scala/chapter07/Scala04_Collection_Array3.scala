package chapter07

object Scala04_Collection_Array3 {

  def main(args: Array[String]): Unit = {

    // TODO Scala 集合 - 数组

    val array1 = Array(1, 2, 3, 4)
    val array2 = Array(5,6,7,8)

    // 循环遍历
    def print(i:Int): Unit ={
      println(i)
    }

//    array1.foreach(print)
    array1.foreach((i:Int)=> {println(i)})  // 使用你们函数
    array1.foreach(i=> println(i))
    array1.foreach(println (_))
    array1.foreach(println)       // 匿名函数省略至简模式，只关注逻辑


  }

}
