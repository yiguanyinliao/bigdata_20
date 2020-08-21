package chapter02

object Scala20_DataType4 {
  def main(args: Array[String]): Unit = {

    // 编译器无法直接计算变量的结果，变量参与运算时，会出现类型转换错误
    // 下面如果换成AA超出常量范围，运行就会报错

    val c : Char = 'A' + 1
    println(c)
  }
}
