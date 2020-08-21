package chapter06

object Scala26_Object_Instance3 {


  def main(args: Array[String]): Unit = {

    // TODO Scala 面向对象 - 构造方法
    //  运行结果=> aaa>ccc>ddd

    val user = new User()

  }


  class Person(name:String){
    println("aaa")
      def this(){
        this("zhangsan")
        println("bbb")
      }

  }
  class User(name:String) extends Person(name){
    println("ccc")
    def this(){
      this("lisi")
      println("ddd")
    }
  }



}

