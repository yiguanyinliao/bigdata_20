import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.DStream
import org.apache.spark.streaming.{Seconds, StreamingContext}


object SparkStreaming07_State {


  def main(args: Array[String]): Unit = {

    // TODO SparkStreaming - 有状态
    //  所谓的有状态应该将每一次采集周期的统计结果进行保存，和后面的统计结果进行合并

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("RDDStream")
    val ssc = new StreamingContext(sparkConf, Seconds(3))
    ssc.checkpoint("cp")  //

    val dstream = ssc.socketTextStream("hadoop102",9999)
    val mapDStream: DStream[(String, Int)] = dstream.flatMap(_.split(" ")).map(w => {(w,1)})

    // TODO reduceByKey称之为无状态统计操作
//     mapDStream.reduceByKey(_+_).print()


    // TODO 有状态操作，sparkStreaming 可以将相同的key的value封装为Seq集合
    //  Option表示缓冲区中相同的key的聚合的值，可能存在，可能不存在
    //  有状态的操作时的缓冲区保存在检查点中,需要设置检查点
    //  requirement failed: The checkpoint directory has not been set. Please set it by StreamingContext.checkpoint()
    mapDStream.updateStateByKey(
      (seq:Seq[Int],buffer:Option[Int]) => {
        val newCount = seq.sum + buffer.getOrElse(0)
        // 返回新的计算结果用于更新缓冲区的值
        Option(newCount)
      }
    ).print()

    ssc.start()
    ssc.awaitTermination()
    //    ssc.stop()
  }

}
