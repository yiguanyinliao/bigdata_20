import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}


object SparkStreaming10_Window1 {


  def main(args: Array[String]): Unit = {

    // TODO SparkStreaming - 窗口操作
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("RDDStream")
    val ssc = new StreamingContext(sparkConf, Seconds(3))
    ssc.checkpoint("cp")

//    ssc.socketTextStream("hadoop102",9999)
//        .flatMap(line => {line.split(" ")})
//        .map((_,1))
//        .reduceByKeyAndWindow(       // 合二为一
//          (x:Int,y:Int) => x + y,
//          Seconds(6),
//          Seconds(3)
//        ).print()

//        .window(Seconds(6),Seconds(3))
//        .reduceByKey(_+_).print()
    // TODO 当窗口在执行聚合操作时，由于滑动步长的原因，会导致有重复数据
    //  如果重复数据过多，会导致统计时间过长，由于已经统计过了，没必要再次统计
    //  可以采用另外一种采集方式进行窗口统计
    //  当前窗口的数据 - 移除的采集周期 + 移入的采集周期

    // TODO requirement failed: The checkpoint directory has not been set. Please set it by StreamingContext.checkpoint()
    //  因为涉及到多个不同的窗口之间的交互，有状态操作在里面需要设定检查点目录
    //  如果没有重复数据计算，不需要设置这个参数，因为有检查点操作，影响性能
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
      ).print()

    ssc.start()
    ssc.awaitTermination()
    //    ssc.stop()
  }

}
