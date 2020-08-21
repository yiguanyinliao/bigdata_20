package chapter02

object Scala_String {
  def main(args: Array[String]): Unit = {
    val name: String = "zhagnsan"
    println("用户名称" + name)

    // JSON
    //    {"name":"zhangsan","age":20}
    println("{\"name\":\"" + name + "\",\"age\":20}")

    printf("{\"name\":\"%s\",\"age\":20}", name)
    println()
    println(s"name=${name.length}")
    println(s"name=${name}, \n age=20")
    println(s"name=$name, \n age=20")

    println(  // 多行字符串
      """
        |name=zhagnsan    // "|"是定格符
        |age = 20
      """.stripMargin
    )
    println("======================================")

    println(  // 多行字符串
      """
        name=zhagnsan
        age = 20
      """.stripMargin
    )
    println(
      """
        |select
        |*
        |from
        |tt
        |where id = 1
      """.stripMargin)

  }
}
