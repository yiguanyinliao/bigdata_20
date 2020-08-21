import org.apache.kafka.clients.consumer.{ConsumerConfig, ConsumerRecord}
import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.InputDStream
import org.apache.spark.streaming.kafka010.{ConsumerStrategies, KafkaUtils, LocationStrategies}
import org.apache.spark.streaming.{Seconds, StreamingContext}


object SparkStreaming06_Transform {


  def main(args: Array[String]): Unit = {

    // TODO SparkStreaming - 无状态
    //  所谓的无状态操作就是方法只对当前的采集周期进行处理的操作
    //  不保留计算结果，常用方法：map、flatMap、filter、reduceByKey
    //  所以这些方法主要应用于底层RDD。

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("RDDStream")
    val ssc = new StreamingContext(sparkConf, Seconds(3))

    val dstream = ssc.socketTextStream("hadoop102",9999)

    // 有的时候，离散化流方法并不完整，如果想要使用更加丰富的功能，可以通过转换
    // 直接获取RDD进行操作。
//    dstream.transform(rdd => rdd.map(_*2)).print()

    // 即使使用了转换操作，那么离散化流也提供了对应的转换功能
    // transform和map的区别：。
//    dstream.map(_*2).print()


    // coding   (OK)  => Driver
    dstream.map(
      // coding   (X)
      s => {
        // coding   (OK)  => Executor
        s * 2
      }
    )


    // 算子、原语、方法（原语在driver端和executor端都有操作）
    // 1.原语外的代码逻辑只会执行一次
    // 2.原语内的代码逻辑会执行多次，因为SparkStreaming会周期性生成rdd
    //    只要生成一个RDD，那么代码逻辑就会执行一次。
    // coding   (OK)  => Driver
    dstream.transform(
      rdd => {
        // coding   (OK) => Driver
        rdd.map(
          s => {
            // coding   (OK)  => Executor
            s * 2
          }
        )
      }
    )


    ssc.start()
    ssc.awaitTermination()
    //    ssc.stop()
  }


}
