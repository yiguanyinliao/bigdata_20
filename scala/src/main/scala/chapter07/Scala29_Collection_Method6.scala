package chapter07


object Scala29_Collection_Method6 {

  def main(args: Array[String]): Unit = {

    // TODO Scala 集合 - 功能函数

    // TODO 1.map : 将集合中的每一个元素转换诚信度数据返回到新的集合中
    //  map方法返回的起始还是一个集合,只不过是其中的元素都是转换后的新的集合
    //  map 方法强调的是元素的变化，而不是集合的变化

    val list = List(1,2,3,4)
//    val listString: List[String] = list.map(str => str.toString)
        val listString: List[String] = list.map(_.toString)
//    list.toSet
    println(list.eq(listString))

    val set = Set(1, 2, 3, 4)
    val setString: Any = set.map(_.toString)
//    set.toList

    // TODO 2.flatten
    //  只能对最外层的元素做扁平化操作

    val list1 = List(List(1,2),List(3,4))
    println(list1.flatten)      // 扁平化操作，将里面两个List合并成一个

    val list2 = List(List(List(1,2),List(3,4)),List(List(5,6),List(7,8)))
    println(list2.flatten)

    println(list2.flatten.flatten)  // 多次调用可以实现多层扁平化

    val list3 = List("hello Scala")
    // TODO flatMap需要传递一个参数，这个参数是一个函数
    //  这个函数的输入是集合中的每一个元素
    //  这个函数的输出是可迭代的集合对象
    println("========================")
    println(list3.flatMap(s => s.split(" ")))
//    list3.flatMap(_.split(" "))
    println("========================")
    val list4 = List(List(1,2),List(3,4))
    println(list4.flatMap(list => list))

    // TODO 下面的集合从原来上不可以进行扁平化
    //  扁平化：将整体拆分成个体
    val list5 = List(1, 2, 3)
    println(list5.flatMap(num => List(num)))  // 返回的还是他自己

    // TODO 3.filter 过滤
    //  contains方法可以拥有判断集合中是否包含元素，但需要类型一致
    val list6 = List(1,2,3,4)
    val list7 = List(1,2)
    val list8 = List("1","2")
    list6.filter(num=>{num%2==0})

    println(list6.filter(num => list7.contains(num)))
    println(list6.filter(num => !list7.contains(num)))
    println(list6.filter(num => list8.contains(num))) // contains会先判断类型
    println(list6.filter(num => list8.contains(num.toString)))

    // TODO 4.group by 分组
    //  group by 返回的是一个map集合，map中的key就是分组的key
    val list9 = List(1,2,3,4)
    println(list9.groupBy(num => num))

    // TODO 5.sortBy 排序
    val list10 = List(1,2,3,4)
    println(list10.sortBy(num => num.toString)(Ordering.String.reverse))
    println(list10.sortBy(num => num))
    println(list10.sortBy(num => num).reverse)  // 倒序

    // sortWith 自己确定自己的逻辑操作
    println(list10.sortWith(
      (left, right) => {
        left > right
      }
    ))

    val user1 = new User()
    user1.age = 30
    user1.salary = 20
    val user2 = new User()
    user2.age = 20
    user2.salary = 10
    val user3 = new User()
    user3.age = 40
    user3.salary = 30

    val list11 = List(user1,user2,user3)
    // TODO Scala中Tuple默认就是可以比较的。
    //      先比较第一个数据大小，相同时，比较第二个 (!!!!经常用)
    println(list11.sortBy(
      user => (user.age, user.salary))(Ordering.Tuple2(Ordering.Int.reverse, Ordering.Int)
    ))



  }
  class User{
    var age :Int = _
    var salary:Int = _

    override def toString: String = {
      s"User[${age}],${salary}]"
    }
  }
}
