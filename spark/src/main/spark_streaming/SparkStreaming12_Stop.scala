import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}


object SparkStreaming12_Stop {


  def main(args: Array[String]): Unit = {

    // TODO SparkStreaming - stop
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("RDDStream")
    val ssc = new StreamingContext(sparkConf, Seconds(3))


    ssc.start()
    ssc.awaitTermination()

    // TODO 优雅的关闭
    //  把未完成的逻辑处理完，Driver端也不会再发送数据

    // TODO 关闭SparkStreaming环境
    //  SparkStreaming中采集一般是不间断的执行采集数据，一般不会停止
    //  Driver程序因为需要一直接收采集器的数据，一般也不会停止
    //  但是在特殊情况下，需要关闭，比如：业务升级、技术升级
    //  SparkStreaming中的stop 方法会真正（强制）的停止整个环境。
//    ssc.stop()

//    // ==================================================
//    new Thread().start() // 正常情况下，停止一个线程是线程的run方法跳出（执行完毕、抛出异常、return）
//    new Thread().stop() // "真正"的停止一个线程其实使用的是stop方法,逻辑可能没有完成
//    // i++ => (赋值、累加)
//    // ==================================================

  }

}
