package chapter07

object Scala30_WordCount {

  def main(args: Array[String]): Unit = {

    // TODO Scala 小练习 - WordCount

    // TODO 需求：统计单词出现的次数，并且安装次数的大小取前3名
    // TODO 1.准备数据

    val list = List(
      "Hello Sacla Spark Flink",
      "Hello Sacla Spark",
      "Hello Sacla",
      "Hello"
    )
    // TODO 2.将数据拆成一个个单词（分词）(flatMap)
    //   string => word
    //   flatMap 可以将数据变的多一些
    val words: List[String] = list.flatMap(
      str => {
        str.split(" ")
      }
    )
    println(words)

    // TODO 3.将相同的单词放置在一个组中，方便统计(group by)
    //    val groupWordMap: Map[String, List[String]] = words.groupBy(word => word)

    val groupWordMap: Map[String, List[String]] = words.groupBy(word => word)
    println(groupWordMap)
    // TODO 4.将分组后的数据进行结构的转换（map）
    //  (word,list) => (word,count)
    val wordCountMap: Map[String, Int] = groupWordMap.map(
      kv => {
        (kv._1, kv._2.size)
      }
    )
    println(wordCountMap)
    // TODO 5.将统计后的结果安装count进行排序(sort)
    val wordToCountList: List[(String, Int)] = wordCountMap.toList
    val sortList: List[(String, Int)] = wordToCountList.sortBy(kv => kv._2)(Ordering.Int.reverse)
    println(sortList)
    // TODO 6.将排序后的结果进行取前3名(take)
    val top3List: List[(String, Int)] = sortList.take(3)
    // TODO 7.将结果打印在控制台(foreach)
    //    println(wordCount)
    top3List.foreach(println)
  }
}
