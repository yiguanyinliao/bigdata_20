import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.DStream
import org.apache.spark.streaming.{Seconds, StreamingContext}


object SparkStreaming08_State1 {


  def main(args: Array[String]): Unit = {

    // TODO SparkStreaming - 有状态
    //  在系统发生异常的情况下，需要重启，重启后需要从之前的检查点中恢复数据
    val ssc = StreamingContext.getOrCreate("cp",()=>{

      val sparkConf = new SparkConf().setMaster("local[*]").setAppName("RDDStream")
      val context = new StreamingContext(sparkConf, Seconds(3))
      context.checkpoint("cp")

      val dstream = context.socketTextStream("hadoop102",9999)
      val mapDStream: DStream[(String, Int)] = dstream.flatMap(_.split(" ")).map(w => {(w,1)})

      mapDStream.updateStateByKey(
        (seq:Seq[Int],buffer:Option[Int]) => {
          val newCount = seq.sum + buffer.getOrElse(0)
          // 返回新的计算结果用于更新缓冲区的值
          Option(newCount)
        }
      ).print()

      context
    })

    ssc.start()
    ssc.awaitTermination()

    //    ssc.stop()
  }

}
