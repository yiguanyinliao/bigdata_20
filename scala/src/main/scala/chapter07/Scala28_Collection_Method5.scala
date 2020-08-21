package chapter07

object Scala28_Collection_Method5 {

  def main(args: Array[String]): Unit = {

    // TODO Scala 集合 - 常用方法
    val list = List(1,2,3,4)
    val list1 = List("scala","spark","hadoop","hive")

    // TODO 过滤数据
    //  按照指定的规则对数据进行筛选过滤
    //  满足条件的数据保留，不满足条件的数据丢弃

    println(list.filter(num => true))
    println(list.filter(num => false))
    println(list.filter((num => num % 2 == 0)))
    println(list.filter((_ % 2 == 0)))

    // TODO 分组
    //  按照指定的规则对数据进行分组操作
    //  指定的规则的结果会作为分组的key，相同的key的数据会放置在一个组当中
    println(list.groupBy(num => num % 2))
    println(list1.groupBy(str => str.substring(0, 1)))
    println(list1.groupBy(str => str.substring(0, 2)))

    // TODO 排序（升序、降序）
    val list2 = List(1,3,2,5,4)
    val list3 = List("1","3","22","55","74")
    // 根据指定的规则对数据进行排序，默认为升序
    println(list2.sortBy(num => num))
    println(list2.sortBy(num => num).reverse)  // 反转
    println(list2.sortBy(num => num)(Ordering.Int.reverse))  // 函数柯里化
    println(list3.sortBy(str => str))   // 字符串按字典序排列
    println(list3.sortBy(str => str.toInt))

    val user1 = new User()
    val user2 = new User()
    val user3 = new User()
    user1.age = 20
    user1.salary = 1000
    user2.age = 30
    user2.salary = 2000
    user3.age = 40
    user3.salary = 3000


    val list4 = List(user2, user1, user3)
    println(list4.sortBy(user=>user.age)(Ordering.Int.reverse))

    // 自定义排序规则
    // Scala中的排序一般是两两排序
    // sortWith的逻辑，需要的排序规则返回true，不需要的规则，返回false
    println(list4.sortWith(
      (left, right) => {
        if (left.age < right.age) {
          true
        } else if (left.age==right.age){
          if(left.salary > right.salary){
            true
          }else{
            false
          }
        }else {
          false
        }

      }
    ))


  }

  class User{
    var age :Int = _
    var salary :Int = _

    override def toString(): String = {
      s"User[${age}],${salary}"
    }
  }
}
