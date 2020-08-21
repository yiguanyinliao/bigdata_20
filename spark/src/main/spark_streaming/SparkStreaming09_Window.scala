import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.DStream
import org.apache.spark.streaming.{Seconds, StreamingContext}


object SparkStreaming09_Window {


  def main(args: Array[String]): Unit = {

    // TODO SparkStreaming - 窗口操作
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("RDDStream")
    val ssc = new StreamingContext(sparkConf, Seconds(3))

    // TODO 所谓的窗口，其实就是将数据的一部分作为整体来使用
    //  在SparkStreaming数据处理中，可以将每一个采集周期当成一个数据，可以设定数据的范围
    //  指定范围的数据进行的操作称为窗口操作
    //  SparkStreaming中的窗口操作，其实就是将多个采集周期的数据作为一个整体来进行操作
    //  一个采集周期数据采集后是不能计算的，需要等待这个窗口中所有的采集周期全部采集完毕，才会进行计算。

    // window 方法就是窗口操作，可以传递两个参数
    // 第一个参数表示窗口的范围（大小）(采集周期的倍数)
    // 第二个参数表示窗口的滑动的幅度（步长）(采集周期的倍数)
    //
    // 如果窗口的滑动的浮动等同于窗口范围，不会出现重复数据
    // 窗口的计算时间取决于步长
    ssc.socketTextStream("hadoop102",9999)
        .flatMap(line => {line.split(" ")})
        .map((_,1))
        .window(Seconds(6),Seconds(3))
        .reduceByKey(_+_).print()


    ssc.start()
    ssc.awaitTermination()
    //    ssc.stop()
  }

}
