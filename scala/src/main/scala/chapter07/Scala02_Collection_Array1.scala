package chapter07

object Scala02_Collection_Array1 {

  def main(args: Array[String]): Unit = {

    // TODO Scala 集合
    // 数组 + 集合
    // Scala中数组也是集合，会有自己的类型


    // TODO 1.数组的定义(创建数组)

    // 创建数组的方式new
//    val array = new Array[String](3)
    // 创建数组的方式：apply
    // 采用伴生对象的apply方法创建Array对象
//    val array = Array.apply(1,2,3,4)  // 采用伴生对象的apply方法生成Array
    val array:Array[Int] = Array(1,2,3,4) // apply的省略方式


    // TODO 2.数据的操作
//    array(0) = "zhagnsan"
//    array(1) = "lisi"
//    array(2) = "wangwu"

    // 修改数据
    // 集合的update方法等同于：集合（索引）= 值
    array(0)=5
    array.update(1,11) // 更新索引1的数据

    // TODO 3.数据的遍历
//    for (name <- array) {
//      println(name)
//    }

    // 将集合数据通过特定格式转为字符串来展示
    println(array.mkString(","))



  }

}
