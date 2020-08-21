package chapter02

object Scala04_Var03 {
  def main(args: Array[String]): Unit = {
    // Scala 语言中有一个非常重要的原则
    // 至简原则，能省则省

    // 变量声明的类型和变量的取值应该是一致的，那么类型可以省去
    val name : String = "zhangsan"
    val age = 20
  }
}
