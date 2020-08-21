import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, InputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}


object SparkStreaming03_Dir {


  def main(args: Array[String]): Unit = {

    // TODO SparkStreaming - 监听目录

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("RDDStream")
    val ssc = new StreamingContext(sparkConf, Seconds(3))

    // TODO 监控文件夹，一旦文件夹内部发生变化，自动读取新增文件
    //  这个功能存在问题，不推荐使用.
    val dirDS: DStream[String] = ssc.textFileStream("input")
//    ssc.socketTextStream()
//    ssc.queueStream()
    dirDS.print()

    ssc.start()

    ssc.awaitTermination()
//    ssc.stop()
  }

}
