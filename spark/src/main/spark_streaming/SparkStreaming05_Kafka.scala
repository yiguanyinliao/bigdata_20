import org.apache.kafka.clients.consumer.{ConsumerConfig, ConsumerRecord}
import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.InputDStream
import org.apache.spark.streaming.kafka010.{ConsumerStrategies, KafkaUtils, LocationStrategies, LocationStrategy}
import org.apache.spark.streaming.{Seconds, StreamingContext}


object SparkStreaming05_Kafka {


  def main(args: Array[String]): Unit = {

    // TODO SparkStreaming - Kafka

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("RDDStream")
    val ssc = new StreamingContext(sparkConf, Seconds(3))

    // TODO Kafka 一般就是用于实时数据传输，所以在SparkStreaming操作过程中
    //  可以使用工具类

    val kafkaPara: Map[String, Object] = Map[String, Object](
      ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG -> "hadoop102:9092,hadoop103:9092,hadoop104:9092",
      ConsumerConfig.GROUP_ID_CONFIG -> "atguigu",
      "key.deserializer" -> "org.apache.kafka.common.serialization.StringDeserializer",
      "value.deserializer" -> "org.apache.kafka.common.serialization.StringDeserializer"
    )

    // 通过工具类访问kafka，传递topic和连接配置即可
    val kafkaDStream: InputDStream[ConsumerRecord[String, String]] = KafkaUtils.createDirectStream(
      ssc,
      LocationStrategies.PreferConsistent,
      ConsumerStrategies.Subscribe[String, String](Set("sparkStream"), kafkaPara)
    )
    // kafka 传递数据时基于kv的键值对数据，所以这里(String,String) 就是键值对
    // 因为一般传递数据时，key不传，所以key为null,获取数据时，只需要获取value即可
    kafkaDStream.map(_.value()).print()

    ssc.start()
    ssc.awaitTermination()
    //    ssc.stop()
  }


}
