package chapter07

import scala.math

object Scala44_Collection_test1 {

  def main(args: Array[String]): Unit = {

    // TODO Scala - 集合 - 课后小练习 不同省份商品点击排行

    val dataList = List(
      ("zhangsan", "河北", "鞋"),
      ("lisi", "河北", "衣服"),
      ("wangwu", "河北", "鞋"),
      ("zhangsan", "河南", "鞋"),
      ("lisi", "河南", "衣服"),
      ("wangwu", "河南", "鞋"),
      ("zhangsan", "河南", "鞋"),
      ("lisi", "河北", "衣服"),
      ("wangwu", "河北", "鞋"),
      ("zhangsan", "河北", "鞋"),
      ("lisi", "河北", "衣服"),
      ("wangwu", "河北", "帽子"),
      ("zhangsan", "河南", "鞋"),
      ("lisi", "河南", "衣服"),
      ("wangwu", "河南", "帽子"),
      ("zhangsan", "河南", "鞋"),
      ("lisi", "河北", "衣服"),
      ("wangwu", "河北", "帽子"),
      ("lisi", "河北", "衣服"),
      ("wangwu", "河北", "电脑"),
      ("zhangsan", "河南", "鞋"),
      ("lisi", "河南", "衣服"),
      ("wangwu", "河南", "电脑"),
      ("zhangsan", "河南", "电脑"),
      ("lisi", "河北", "衣服"),
      ("wangwu", "河北", "帽子")
    )

    val list = dataList.map(
      t => {
        t._2 + "-" + t._3
      }
    )
    println(list)

    val groupMap = list.groupBy(data => data)
    println(groupMap)

    val mapSize = groupMap.map(kv => (kv._1,kv._2.size))
    println(mapSize)

    val mapSizeList = mapSize.toList  // TODO 避免在Map中被更新替换
    println(mapSizeList)

    val prvToItemCountList = mapSizeList.map(kv => {
      var str = kv._1
      var count = kv._2
      var list1 = str.split("-")
      (list1(0), (list1(1), count))
    })
    println(prvToItemCountList)
    
    val prvGroupMap = prvToItemCountList.groupBy(t => t._1)
    println(prvGroupMap)

    val result = prvGroupMap.mapValues(
      list => list.map(t => t._2).sortWith((left, right) => left._2 > right._2)
//      list => list.map(t => t._2).sortBy(_._2)(Ordering.Int.reverse)  // TODO 使用这种方法也可以
    )
    println(result)



//    val ww = dataList.map(Tuple1 => Tuple1._1 -> List(Tuple1._2 + Tuple1._3))
//    val ww = dataList.map(Tuple1 => Tuple1._1 -> (Tuple1._2 + Tuple1._3))
//    val gg = ww.groupBy(list => list)
//    gg.map(kv => {
//      (kv._1, kv._2.size)
//    }).toList.sortBy(_._2)(Ordering.Int.reverse).foreach(println)

  }
}
