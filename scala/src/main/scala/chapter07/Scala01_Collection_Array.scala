package chapter07

object Scala01_Collection_Array {

  def main(args: Array[String]): Unit = {

    // TODO Scala 集合
    // 数组 + 集合
    // Scala中数组也是集合，会有自己的类型
    // TODO 1.数组的定义(创建数组)
    //  Java:String[] ss = new String[2]

    val array = new Array[String](3)

    // TODO 2.数据的操作
    //  scala中括号有特殊用途，表示泛型，所以无法使用中括号访问数组的数据
    //  scala采用小括号的方式访问
    //  Java:ss[0] = "zhangsan"

    array(0) = "zhagnsan"
    array(1) = "lisi"
    array(2) = "wangwu"

    // TODO 3.数据的遍历
    //  Java:for(i= 0:i<size,i++)

    for (name <- array) {
      println(name)
    }
  }

}
