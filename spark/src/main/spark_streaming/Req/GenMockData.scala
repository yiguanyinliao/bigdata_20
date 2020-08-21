package Req

import java.util.Properties

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord}

import scala.collection.mutable.ListBuffer
import scala.util.Random

object GenMockData {

  // TODO SparkStreaming案列实操
  def main(args: Array[String]): Unit = {

    // 数据格式：时间戳 区域 城市 用户ID 广告ID
    // 数据使用空格进行分割
    // 每一条数据（字符串）表示了用户点击某个广告一次。

    val topic = "sparkStream"

    // Kafka 集群配置
    val prop: Properties = new Properties()
    prop.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"hadoop102:9092")
    prop.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer")
    prop.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer")

    // TODO 使用kafka的生产者对象将数据发送到kafka中
    //  设定kafka的配置信息，用于构造生产者对象.
    val producer = new KafkaProducer[String,String](prop)

    // 发送数据
    while (true) {
      for (data <- genDate()){
        producer.send(new ProducerRecord[String,String](topic,data))
        println(data)
      }
      Thread.sleep(2000)

    }

  }
  private def genDate()= {

    val areaList = List("华北","东北","华南")
    val cityList = List("北京","上海","深圳")

    val buffer = ListBuffer[String]()
    for (i <- 0 to new Random().nextInt(50)) {
      val s = "111111 华北 北京 1 1"
      val area = areaList(new Random().nextInt(3))
      val city = cityList(new Random().nextInt(3))
      val userId = 1 + new Random().nextInt(6)
      val adId = 1 + new Random().nextInt(6)
      buffer.append(s"${System.currentTimeMillis()} ${area} ${city} ${userId} ${adId}")
    }
    buffer
  }

}
