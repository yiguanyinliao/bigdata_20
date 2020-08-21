package chapter05

object Scala04_Functon_Normal1 {


  def main(args: Array[String]): Unit = {


    // TODO 函数参数是不是无限多？
    //  函数的参数不能超过22个，可以声明和使用，但是不能给其他用户用
    def fun1(
              name: Int,
              name1: Int,
              name2: Int,
              name3: Int,
              name4: Int,
              name5: Int,
              name6: Int,
              name7: Int,
              name8: Int,
              name9: Int,
              name10: Int,
              name11: Int,
              name12: Int,
              name13: Int,
              name14: Int,
              name15: Int,
              name16: Int,
              name17: Int,
              name18: Int,
              name19: Int,
              name20: Int,
              name21: Int,
              name22: Int,
              name23: Int
            ):
    Unit = {
      println("test.....")

    }
    //    fun1(1,2,3,4,5,6,7,8,9,10,11,12,13,1,1,1,1,1,1,1,1,1,1,1)
    //    val a = fun1 _

    // TODO 如果想要传递过多参数，可以将参数的类型设定为集合类型或可变类型
    //  可变参数，java中使用...，参数可能有，也可能没有。
    //  scala中使用*
    //  如果使用可变参数，不传递参数，那么获取的是空集合List()
    //  如果传递这个参数，那么获取的是包装数组，
    def fun2(name: String*): Unit = {
      println(name)

    }

    fun2()
    fun2("zhangsan", "wangwu")


    // TODO 可变参数应该放在参数列表的最后
    //  参数不应该有多个可变参数
    def fun3(password: String, name: String*): Unit = {
      println("1234234", "ssss", "aaaaaa")
    }

    // TODO 改变参数
    //  Scala中函数的参数不能改，使用val声明
    //  如果要给参数提供默认值，scala在参数后直接增加默认值
    def regUser(account: String, password: String = "12456"): Unit = {
//      if (password == null) {
//        password = "123456"
//      }
      println(account,password)

    }
    // 如果有默认值，可以不传
    regUser("zhagnsan")
    // 如果传值，则覆盖默认值
    regUser("zhagnsan","000000")

    def regUser1(account: String, password: String = "12456",tel:String): Unit = {
      //      if (password == null) {
      //        password = "123456"
      //      }
      println(account, password,tel)
    }
    // 函数的参数在调用时匹配规则从左到右依次匹配
    regUser1("lisi","123","213")
    // scala 提供了一种语法，解决了默认值调用的问题，为带名参数传递
    // TODO 带名参数
    regUser1("lisi",tel = "123")
    // TODO 可以带名调换参数顺序
    regUser1(tel = "123", account = "lisi")

  }

}
