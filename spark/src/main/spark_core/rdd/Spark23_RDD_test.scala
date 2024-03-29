package rdd

import java.text.SimpleDateFormat
import java.util.Date

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark23_RDD_test {

  def main(args: Array[String]): Unit = {


    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)

    // TODO 小练习：将List("Hello", "hive", "hbase", "Hadoop")根据单词首写字母进行分组。
    val list = List("Hello", "hive", "hbase", "Hadoop")

    val rdd = sc.makeRDD(list)

    rdd.groupBy(s => {
//      s.charAt(0)
//      s.substring(0)
      s(0)
    }).collect().foreach(println)


    // TODO 小练习：从服务器日志数据apache.log中获取每个时间段访问量
    //  (HH,count)。

    var rdd1 = sc.textFile("input/apache.log")
    val rdd2 = rdd1.map(
      line => {
        val datas = line.split(" ")
        val dateString = datas(3)
        val sdf = new SimpleDateFormat("dd/MM/yyyy:HH:mm:ss")
        val sdf1 = new SimpleDateFormat("HH")
        val c: Date = sdf.parse(dateString)
        sdf1.format(c)
      }
    )
    val time: RDD[(String, Iterable[String])] = rdd2.groupBy(time=>time)
    val wordCount = time.mapValues(_.size)
    wordCount.collect().foreach(println)

    sc.stop()

  }

}
