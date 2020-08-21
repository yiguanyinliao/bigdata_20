package chapter066


object Scala05_Object_Package3 {


    def main(args: Array[String]): Unit = {

      // TODO Scala面向对象编程 -包
      //  1.package和源码文件的物理路径没有关系
      //  scala 会将源码程序安装package路径编译为class文件
      //  2.package包名体现的不好：包的全路径
      //   package 关键字可以多次声明
      //   当前类编译后会在最后的package关键字所在的包中生成类文件
      //  3.package 可以使用层级结构
      //   可以设定作用域，父包中的类在子包中可以直接访问
      //  4.Scala 是完全面向对象的语言，package也有面向对象的语法
      //    Scala 使用了一种包对象的语法，将包当成对象来用
      //    包对象可以声明属性和方法，在同一包中可以共享其属性和方法
      //    可以将包中共通性的代码封装到包中
      println(name)
      test()

    }

  }





