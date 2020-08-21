package chapter07

object Scala43_Collection_test {

  def main(args: Array[String]): Unit = {

    // TODO Scala - 集合 - 课后小练习 计算Word Count

    // TODO 方法一：
    val dataList = List(
      ("Hello Scala", 4), ("Hello Spark", 2)
    )

    val words = dataList.map(
      tuple => {
        val str = tuple._1
        var count = tuple._2
        (str + " ") * count
      }
    )
    println(words)

    val wordList = words.flatMap(_.split(" "))
    println(wordList)

    val mapGroup = wordList.groupBy(word => word)
    println(mapGroup)

    val stringToInt = mapGroup.map(
      kv => {
        (kv._1, kv._2.size)    // TODO 使用元祖的形式表示一个KV键值对
      }
    )
    println(stringToInt)
    stringToInt.foreach(println)
    // TODO 简略版方法一：
    println("======= 简略版方法一 ========")
    dataList.map(t => (t._1 + " ")*t._2).flatMap(_.split(" ")).groupBy(w => w).map((kv => (kv._1,kv._2.size))).foreach(println)

    // TODO 方法二：
    println("======= 方法二 ========")
    val wordToCountList = dataList.flatMap(
      t => {
        val strList = t._1.split(" ")
        var count = t._2
        strList.map(w => (w, count))
      }
    )
    println(wordToCountList)
    val groupMap = wordToCountList.groupBy(tuple => tuple._1)
    println(groupMap)
    val wordToSumMap = groupMap.map(
      kv => {
        val str = kv._1
        val list = kv._2
        val countList = list.map(tuple => tuple._2)
        (str, countList.sum)
      }
    )
    println(wordToSumMap)
    println("======= 简化版1方法二 ========")
    // TODO 如果在数据处理中，数据类型为KV类型，那么当K保持不变，只对V进行操作时，可以采用mapValues方法
    val wordToSumMap1 = groupMap.mapValues(
      list => {
        list.map(tuple => tuple._2).sum
      }
    )
    println(wordToSumMap1)

  }
}
