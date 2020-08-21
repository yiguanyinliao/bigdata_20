package chapter06



object Scala06_Object_Import {


    def main(args: Array[String]): Unit = {

      // TODO Scala 面向对象编程 - 导入

      // Java: 导类（主要功能是导类，而不是导包、静态导入
      // Java 语法的impor功能比较单一，但是不能省略
      // Scala语言在java基础上进行了扩展


      // Scala import
      // TODO 1.import 可以声明在任意的位置
//      import java.sql.Date
//      new Date()
      // TODO 2.java默认导入的类是java.lang包中所有的类
      //       Scala 默认导入的类：
      //        2.1 Java.lang包中的类
      //        2.1 Scala包中的所有的类
      //        2.3 Perdef类（类似于Java的静态导入）
      println("zhangsan")  // println 就是Perdef中的类

      // TODO 3. Scala 可以导包，也可以导类。Java应该是导类
//      import java.util
//      util.ArrayList

      // TODO 4. Scala 可以导入一个包中所有的类
      //  Scala 使用 _ 代替java中的 *
      //  即使使用_导入包中所有的类，其实编译器也是按需导入，没有导入所有的类
//      import java.util._
//      new ArrayList()

      // TODO 5. Scala也可以在一行中导入一个包中多个类
      //  使用{} 将多个类包含起来
      import java.util.{ArrayList,HashMap,HashSet}
      import java.util.ArrayList
      import java.util.HashMap
      import java.util.HashSet

      // TODO 6.使用import的相关语法可以将指定的类隐藏
      import java.util._
      import java.sql.{Date=>_,_} // 将指定的类隐藏
      new Date(123123123)

      // TODO 7.使用import可以给类起别名

      import java.util.{HashMap => JavaHashMap}   // 起别名
      new JavaHashMap()

      type S = String
      new S()

      // TODO 8.使用包名访问类的时候，这个类的所在包其实采用的相对路径查找
      //  如果不想使用相对路径查找类，可以采用特殊的路径（_root_）来访问
      new Java.util.HashMap()
      new _root_.java.util.HashMap()

    }

  }

package Java{
  package util{
    class HashMap{

    }
  }
}




