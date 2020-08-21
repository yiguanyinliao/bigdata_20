package basic

import autumn_frameworker.core.TService
import org.apache.spark.rdd.RDD

class WordCountService extends TService{

  private val wordCountDao = new WordCountDao()

  /**
    * 分析
    */
  override def analysis()={
    val lines: RDD[String] = wordCountDao.fromFile("input/word.txt")
    val words: RDD[String] = lines.flatMap(line => line.split(" "))
    val group: RDD[(String, Iterable[String])] = words.groupBy(word => word)
    val wordToCount: RDD[(String, Int)] = group.mapValues(list=>list.size)
    wordToCount.collect()

  }
}
