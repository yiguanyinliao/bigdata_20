package basic

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark01_WordCount {

  def main(args: Array[String]): Unit = {

    // TODO 使用Spark框架来完成WordCount的统计

    // TODO 1.获取Spark框架的环境连接
    //  SparkContext

    //  创建Spark的基础配置
    // local 表示本地环境，使用一个Cpu核来处理，如果使用并发处理，需要配置后面的核数
    // 如果不确定使用多少个核数，可以默认使用最大CPU核数，后面配置local[*]、
    val sparkConf = new SparkConf().setMaster("local").setAppName("Spark-WordCount")
//    val sparkConf = new SparkConf().setMaster("local[2]").setAppName("Spark-WordCount")
//    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Spark-WordCount")
    val sc: SparkContext = new SparkContext(sparkConf)

    // TODO 2.对象Spark环境对数据进行处理
    // 2.1 从文件中读取数据
    val lines: RDD[String] = sc.textFile("input/word.txt")
//    println(lines)
// 2.2 将每一行的字符串进行分词操作：扁平化
    val words: RDD[String] = lines.flatMap(line => line.split(" "))
//    println(words)
// 2.3 将相同的单词分在一个组中
    val group: RDD[(String, Iterable[String])] = words.groupBy(word => word)
//    println(group)
    // 2.4 将分组后的数据进行统计（Iterable => length）
    val wordToCount: RDD[(String, Int)] = group.mapValues(list => list.size)
    // 2.5 将统计的结果采集后，打印在控制台
    wordToCount.collect().foreach(println)

    // TODO 3.将环境（连接）关闭
    sc.stop()



  }

}
