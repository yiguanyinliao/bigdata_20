import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Duration, Seconds, StreamingContext}

object SparkStreaming01_WordCount {


  def main(args: Array[String]): Unit = {

    // TODO SparkStreaming - WordCount

    // TODO 构建环境
    //  SparkStreaming是基于SparkCore开发的，会对Spark环境对象进行封装
    //  创建SparkStreaming对象时，需要传递2个参数
    //    1.SparkConf配置对象
    //    2.采集周期
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("StreamWordCount")
//    val ssc = new StreamingContext(sparkConf, Duration(3))
        val ssc = new StreamingContext(sparkConf, Seconds(3))

    // TODO 执行计算
    //  从socket中获取数据.
    val DStream: ReceiverInputDStream[String] = ssc.socketTextStream("hadoop102",9999)

    // 将一行一行的数据分解成一个一个单词，扁平化
    val wordDStream: DStream[String] = DStream.flatMap(_.split(" "))

    // 将单词转换为特定的结构 word => (word,1)
    val wordToOneDStream: DStream[(String, Int)] = wordDStream.map((_,1))
    // 计算单词的出现字数
    val wordToCountDStream: DStream[(String, Int)] = wordToOneDStream.reduceByKey(_+_)
    // 打印结果
    wordToCountDStream.print()

    // TODO 释放连接
    //  SparkStreaming stop 方法不能直接调用，一旦调用，driver端就停止了，就无法处理数据
    //  如果main方法执行完毕，Driver程序会执行完成自动结束
    //  所以不能让main方法执行完毕，需要等待采集器的结束.
    ssc.start()    // 采集器独立启动
    ssc.awaitTermination()
//    ssc.stop()
  }

}
