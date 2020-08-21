import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext, StreamingContextState}


object SparkStreaming13_Stop1 {


  def main(args: Array[String]): Unit = {

    // TODO SparkStreaming - stop
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("RDDStream")
    val ssc = new StreamingContext(sparkConf, Seconds(3))

    val socketDS = ssc.socketTextStream("hadoop102", 9999)

    socketDS.flatMap(_.split(" "))
      .map((_, 1))
      .reduceByKey(_ + _)
      .print()


    ssc.start()

    // TODO stop 方法需要采用新的线程来执行，不能在main线程操作
    new Thread(new Runnable {
      override def run(): Unit = {

        // TODO stop 方法的调用不应该是线程启动后马上执行
        //  stop方法应该存在调用的时机
        //  调用的时机不容易确定，需要周期性的判断时机是否出现
        //  详情代码见课件！！！！

        while (true) {
          Thread.sleep(10000)
          // 关闭的方法一般不会在程序内部完成
          // 一般采用第三方的程序或者存储进行判断
          // HDFS => /stopSpark
          // zk => /node => stop
          // mysql => stopSpark =0 => 1(关闭)
          // redis => stopSpark =0 => 1(关闭)，

          val state: StreamingContextState = ssc.getState()
          if (state == StreamingContextState.ACTIVE) {
//            ssc.stop()  // 强制关闭(不使用)
            ssc.stop(true,true)  // 优雅的关闭
            System.exit(0)

          }
        }
      }
    }).start()

    ssc.awaitTermination()


  }

}
