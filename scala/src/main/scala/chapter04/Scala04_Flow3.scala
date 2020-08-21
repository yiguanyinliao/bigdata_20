package chapter04

object Scala04_Flow3 {


  def main(args: Array[String]): Unit = {

    val age = 20

    val result = if (age == 20) {
      "zhangsan"
    } else{
       1
    }

    // result => Any
    println(result)

  }

}
