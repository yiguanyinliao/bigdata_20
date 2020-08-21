package chapter06

object Scala07_Object_Import1 {


    def main(args: Array[String]): Unit = {

      // TODO Scala 面向对象编程 - 导入对象
      val user = new User()  // 不能将val转换成var，必须用val声明

      // 简化开发，需要考虑冲突的问题
      import user._   // 表示导入对象里所以的东西
      test1()
    }

  }



    class User{
      def test1()={
        println("test ....")
      }
    }

