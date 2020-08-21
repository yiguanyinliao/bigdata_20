package chapter04

object Scala01_Flow_scala {

  def main(args: Array[String]): Unit = {


    val age = 20;

    if(age == 20) {
      println("年龄为20")
    }

    if (age == 30) {
      println("是30")
    } else{
      println("不是30")
    }

    if (age < 12) {
      println("少年")
    }else if (age >= 12 && age < 30) {
      println("青年")
    }else if (age >= 30 && age < 50) {
      println("中年")
    }else{
      println("老年")
    }



  }

}
