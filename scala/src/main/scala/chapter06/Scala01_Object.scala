package chapter06

object Scala01_Object {


  def main(args: Array[String]): Unit = {

    // Scala 面向对象编程
    /*
      package com.atguigu.bigdata
      import java.util.Date;
      public class User{           // TODO Java声明类
        private String name;
        public void setName(String name){
          this.name = name;
          }
          public String getName(){
            return this.name;
          }
      }

      User user = new User() // TODO Java创建对象
      user.name
      user.setName("lisi")

     */

    val user:User01 = new User01()   // TODO Scala创建对象
    println(user.name)
    user.test()

  }

}


class User01{     // TODO Scala声明类
  var name:String = "zhangsan"
  def test()={
    println("user01.....")
  }
}
