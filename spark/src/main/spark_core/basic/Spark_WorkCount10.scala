package basic

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark_WorkCount10 {

  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local").setAppName("Spark-WordCount")
    val sc = new SparkContext(sparkConf)


    val lines: RDD[String] = sc.textFile("input/word.txt")
    val words: RDD[String] = lines.flatMap(line => line.split(" "))


    // TODO 1.groupBy
    val rdd1 = sc.makeRDD(
      List(
        "hello","hello","spark"
      )
    )
    rdd1.groupBy(word => word).mapValues(_.size).collect().foreach(println)

    // TODO 2.groupByKey
    val rdd2 = sc.makeRDD(
      List(
        ("hello",1),("hello",2),("spark",1)
      )
    )
    rdd2.groupByKey().mapValues(_.sum).collect().foreach(println)

    // TODO 3.reduceBykey
    val rdd3 = sc.makeRDD(
      List(
        ("hello",1),("hello",2),("spark",1)
      )
    )

    rdd3.reduceByKey(_+_).collect().foreach(println)
    // TODO 4.aggregateByKey
    val rdd4 = sc.makeRDD(
      List(
        ("hello",1),("hello",2),("spark",1)
      )
    )
    rdd4.aggregateByKey(0)(_+_,_+_)
    // TODO 5.foldByKey
    val rdd5 = sc.makeRDD(
      List(
        ("hello",1),("hello",2),("spark",1)
      )
    )
    rdd5.foldByKey(0)(_+_)
    // TODO 6.combineByKey
    val rdd6 = sc.makeRDD(
      List(
        ("hello",1),("hello",2),("spark",1)
      )
    )
    rdd6.combineByKey(num=>num,(x:Int,y)=>{x+y},(x:Int,y:Int)=>{x+y}).collect().foreach(println)
    // TODO 7.countByKey
    val rdd7 = sc.makeRDD(
      List(
        ("hello",1),("hello",2),("spark",1)
      )
    )
    rdd7.countByKey().foreach(println)
    // TODO 8.countByValue
    val rdd8 = sc.makeRDD(
      List(
        "hello","hello","spark"
      )
    )
    rdd8.countByValue().foreach(println)
    // TODO 9.aggregate
    val rdd9 = sc.makeRDD(
      List(
        "hello","hello","spark"
      )
    )
    val zeroValue = Map[String,Int]()
    val wordCountMap = rdd9.aggregate(zeroValue)(
      (map, word) => {            // 和初始值合并
        map.updated(word,map.getOrElse(word,0) + 1)
      },
      (map1, map2) => {           // 分区间合并
        // TODO 两个map合并
        map1.foldLeft(map2)(      // map2作为初始值和map1的kv对合并
          (map,kv)=>{
            val word =kv._1
            val count =kv._2
            map.updated(word,map.getOrElse(word,0) + count)
          }
        )
      }
    )
    wordCountMap.foreach(println)

    // TODO 10.fold

    val rdd10 = sc.makeRDD(
      List(
        "hello","hello","spark"
      )
    )
    val zeroValue10 = Map[String, Int]()
    val rdd10Map = rdd10.map(word => {Map[String,Int](word->1)})
    val wordCountMap10 = rdd10Map.fold(zeroValue10)(
      (map1, map2) => {
        // TODO 两个map合并
        map1.foldLeft(map2)(
          (map,kv)=>{
            val word =kv._1
            val count =kv._2
            map.updated(word,map.getOrElse(word,0) + count)
          }
        )
      }
    )
    println(wordCountMap10)


    // TODO 11.reduce
    val rdd11 = sc.makeRDD(
      List(
        "hello","hello","spark"
      )
    )
    val rdd11Map = rdd11.map(word => {Map[String,Int](word ->1)})
    val wordCountMap11 = rdd11Map.reduce(
      (map1, map2) => {
        map1.foldLeft(map2)(
          (map, kv) => {
            val word = kv._1
            val count = kv._2
            val oldValue = map.getOrElse(word, 0)
            map.updated(word, oldValue + count)
          }
        )
      }
    )

    println(wordCountMap11)

    sc.stop()
  }

}
