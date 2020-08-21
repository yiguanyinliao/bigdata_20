package chapter06
package ParentPackage
package ChildPackage

object Scala03_Object_Package1 {


  def main(args: Array[String]): Unit = {

    // TODO Scala面向对象编程 -包
    //  1.package和源码文件的物理路径没有关系
    //  scala 会将源码程序安装package路径编译为class文件
    //  2.package包名体现的不好：包的全路径
    //   package 关键字可以多次声明
    //   当前类编译后会在最后的package关键字所在的包中生成类文件
    //  3.package 可以使用层级结构
    println("zhangsan")

  }

}

