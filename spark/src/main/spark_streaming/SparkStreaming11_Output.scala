import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}


object SparkStreaming11_Output {


  def main(args: Array[String]): Unit = {

    // TODO SparkStreaming - 窗口操作
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("RDDStream")
    val ssc = new StreamingContext(sparkConf, Seconds(3))
    ssc.checkpoint("cp")

    ssc.socketTextStream("hadoop102",9999)
      .flatMap(line => {line.split(" ")})
      .map((_,1))
      .reduceByKeyAndWindow(
        (x:Int,y:Int) => {
          println(s"x = $x,y = $y")
          x + y
        },
        (a:Int,b:Int) => {
          println(s"a = $a,b = $b")
          a -b
        },
        Seconds(6),
        Seconds(3)
      )
//      .print()   // 必须得有输出，否则会报错
        .foreachRDD(  // 对DStream里的每个rdd做操作
      rdd => {
        rdd.map((_,1))
      }
    )

    // foreachRDD 可以对采集的结果进行遍历
    // transform(rdd => rdd)
    // foreachRDD & transform的区别和rdd中foreach & map 的区别一样
    // rdd => map => (A->B) => new RDD
    // rdd => foreach => A->U => RunJob

    // TODO requirement failed: No output operations registered, so nothing to execute
    //  如果没有输出的话，因为数据是一直进入系统中，如果不进行处理，那么数据会丢失
    //  DStream 必须做输出，rdd可以没有行动算子

    ssc.start()
    ssc.awaitTermination()
    //    ssc.stop()
  }

}
