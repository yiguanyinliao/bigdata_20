import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.dstream.InputDStream
import org.apache.spark.streaming.{Seconds, StreamingContext}

import scala.collection.mutable

object SparkStreaming02_Queue {


  def main(args: Array[String]): Unit = {

    // TODO SparkStreaming - rddQueue

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("RDDStream")
//    val ssc = new StreamingContext(sparkConf, Duration(3))
    val ssc = new StreamingContext(sparkConf, Seconds(3))

    val rddQueue: mutable.Queue[RDD[Int]] = new mutable.Queue[RDD[Int]]()

    // TODO 执行计算
    val ds: InputDStream[Int] = ssc.queueStream(rddQueue)

    ds.print()

    ssc.start()

    for (i <- 1 to 5) {
      rddQueue += ssc.sparkContext.makeRDD(1 to 300, 10)
      Thread.sleep(2000)
    }

    ssc.awaitTermination()
//    ssc.stop()
  }

}
