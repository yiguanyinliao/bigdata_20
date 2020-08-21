package chapter06

object Scala39_Object_Interface4 {


  def main(args: Array[String]): Unit = {


    // TODO OCP => 开闭原则
    //  开发程序是，允许对功能的扩展开放，对代码的修改要关闭
    //

    // TODO Scala 面向对象 - 特质

    // TODO 使用特质给对象动态扩展功能
    val mysql = new MySQL() with InsertData  // 通过with动态混入
    mysql.select()
    mysql.insert()



  }

  trait InsertData{
    def insert()={
      println("insert ....")
    }
  }
//  class NewSql{
//
//    def insert()={
//      println("insert ....")
//    }
//  }

//  class MySQL extends NewSql{
  class MySQL{

    def select()={
      println("select ....")
    }
  }

}

