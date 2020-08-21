import java.io.{BufferedReader, InputStreamReader}
import java.net.Socket

import org.apache.spark.SparkConf
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.receiver.Receiver
import org.apache.spark.streaming.{Seconds, StreamingContext}


object SparkStreaming04_Receiver {


  def main(args: Array[String]): Unit = {

    // TODO SparkStreaming - Receiver

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("RDDStream")
    val ssc = new StreamingContext(sparkConf, Seconds(3))

    // TODO 自定义数据采集器
    val ds = ssc.receiverStream(new MyReceiver("hadoop102",9999))
    ds.print()

    ssc.start()
    ssc.awaitTermination()
    //    ssc.stop()
  }

  /**
    * 自定义数据采集器
    * 1.继承Receiver，定义泛型并传递存储级别参数
    * 2.重写方法
    */

  class MyReceiver(host: String, port: Int) extends Receiver[String](StorageLevel.MEMORY_ONLY) {

    private var socket: Socket = _

    def receive() = {
      // 接收数据
      // socket一行一行传递数据。
      var bufferReader = new BufferedReader(
        new InputStreamReader(
          socket.getInputStream, "UTF-8"
        )
      )
      while (true) {
        val line = bufferReader.readLine()
        // 将获取的数据进行封装
        store(line)    // TODO 框架中提供的封装方法
        Thread.sleep(1000)
      }
    }

    override def onStart(): Unit = {
      socket = new Socket(host,port)
      new Thread("Socket Receiver"){
        override def run() ={
          receive()
        }
      }.start()

    }

    override def onStop(): Unit = {
      if (socket != null) {
        socket.close()
        socket = null
      }

    }
  }

}
