package chapter10

object Scala05_Transform04 {


  def main(args: Array[String]): Unit = {

    // TODO 隐式转换 - 隐式参数、隐式变量
    def regUser(implicit password:String = "000000"): Unit ={
      println("注册用户，默认密码：" + password)

    }
    regUser("zhangsan")
    regUser()
    regUser     // TODO 函数的参数类别没有参数或者没有声明参数列表，可以省略小括号()  (X)

    // 如果函数的参数预计是有可能需要修改的，可以使用隐式参数来声明
    // TODO 隐式参数： 在函数的参数前增加关键字
    //  可以在编译的时候将隐式变量自动传递到参数中。

    // TODO 隐式变量： 在变量前增加关键字
    //  如果使用隐式参数，就好像没有声明参数列表一样，那么这个参数列表可以省略
    //  如果调用函数时使用了(),意味着不使用隐式变量，使用的是列表中的参数。

    implicit val pawd = "123123"
//    implicit val aaa = "123124"  // 不能同时有多个隐式参数
    regUser      // TODO 加上implicit关键字为隐式参数，不加括号也是可以的    123123
    regUser()   //  000000

    val list = List(1,2,3,4)
    list.sortBy(num=> num) // 这里有隐式转换
    list.sortBy(num=> num)(Ordering.Int.reverse)  // 写上后面的表示使用自己的规则

  }
}


