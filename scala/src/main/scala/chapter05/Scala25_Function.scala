package chapter05

import sun.tools.jconsole.Plotter

object Scala25_Function {

  def main(args: Array[String]): Unit = {

    // TODO Scala 函数 -函数是什么？
    //  Java中没有函数概念，编译后的class文件也不可能有函数
    //  Scala有函数的概念，但是编译后的class文件不能有函数的概念
    //  那么函数到底是什么？


    // TODO 1.Scala编译器会将函数在编译时转换为当前类的静态、final的方法
    def test()={
      println("abc")
    }

    def fun():()=>Unit={
      test _
    }

    // TODO 2.将函数赋值给变量的时候，那么编译器会在编译的时候产生匿名函数
    //  产生一个特殊的对象，调用其固定的方法，执行逻辑
    val a = test _
    a()
//    test()

    // TODO 3.Scala中函数式编程其实在编译后为实现为类、方法、对象
    //  通过组合实现其功能
//    fun()
    fun()()


  }
}