package basic

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark02_WordCount1 {

  def main(args: Array[String]): Unit = {

    // TODO 使用reduceByKey操作要比groupBy，map性能要高

    // 建立连接
    val sparkConf = new SparkConf().setMaster("local").setAppName("Spark-WordCount")
    val sc = new SparkContext(sparkConf)
    val lines: RDD[String] = sc.textFile("input/word.txt")
    val words: RDD[String] = lines.flatMap(line => line.split(" "))

    // 转换单词结构 word => (word,1
    val wordToOne: RDD[(String, Int)] = words.map(
      word => {
        (word, 1)
      }
    )
    val wordToSum: RDD[(String, Int)] = wordToOne.reduceByKey(_+_)
    wordToSum.collect().foreach(println)

    // 关闭连接
    sc.stop()
  }

}
