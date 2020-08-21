package chapter10

object Scala04_Transform03 {


  def main(args: Array[String]): Unit = {

    val s = "zhangsan"
    println(s.substring(0,1))
    println(s.charAt(0))
    println(s.toCharArray()(0))
    println(s(0))           // 隐式转换
    println(s.apply(0))



    // 类名
    // User() => User.apply()
    // 对象名
    // user() => obj.apply()。

    // user => update(Ext)        => user => Ext
    // string => apply(StringOps) => String => StringOps   // 把字符串当做对象生成伴生对象apply()
    // TODO import  (默认导入)
    //  java.lang._
    //  scala._
    //  scala.Predef
    // TODO StringOps（值类型）类为String的扩展类，有scala编译器自动转换、
    println()
  }


}


