package Req

import java.text.SimpleDateFormat

import org.apache.kafka.clients.consumer.{ConsumerConfig, ConsumerRecord}
import org.apache.spark.SparkConf
import org.apache.spark.sql.catalyst.expressions.Minute
import org.apache.spark.streaming.dstream.{DStream, InputDStream}
import org.apache.spark.streaming.kafka010.{ConsumerStrategies, KafkaUtils, LocationStrategies}
import org.apache.spark.streaming.{Minutes, Seconds, StreamingContext}

object Req3_LastHourAdAnalysis {


  def main(args: Array[String]): Unit = {

    // TODO 需求：将每天对某个广告点击超过100次的用户拉黑

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("BlackList")
    val ssc = new StreamingContext(sparkConf, Seconds(5))

    val kafkaPara: Map[String, Object] = Map[String, Object](
      ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG -> "hadoop102:9092,hadoop103:9092,hadoop104:9092",
      ConsumerConfig.GROUP_ID_CONFIG -> "spark",
      "key.deserializer" -> "org.apache.kafka.common.serialization.StringDeserializer",
      "value.deserializer" -> "org.apache.kafka.common.serialization.StringDeserializer"
    )

    val kafkaDStream: InputDStream[ConsumerRecord[String, String]] = KafkaUtils.createDirectStream(
      ssc,
      LocationStrategies.PreferConsistent,
      ConsumerStrategies.Subscribe[String, String](Set("sparkStream"), kafkaPara)
    )

    // TODO 1.读取Kafak中广告点击的数据
    //        将数据转换类来声明

    val clickDStream: DStream[AdClickData] = kafkaDStream.map(record => {
      val data = record.value()
      val datas = data.split(" ")
      AdClickData(datas(0), datas(1), datas(2), datas(3), datas(4))
    })

    // TODO 将数据进行结构的转换，便于统计分析
    //  1分钟，每10秒统计结果
    //  12:01 => 12:00
    //  12:09 => 12:00
    //  12:31 => 12:30

    // ((A,12:01),1) => ((A,12:00),1)
    // ((A,12:02),1) => ((A,12:00),1)
    // ((A,12:05),1) => ((A,12:00),1)
    val mapDS = clickDStream.map(
      data => {
        ((data.adId, data.ts.toLong / 10000 * 10000), 1)
      }
    )

    // shuffle:打乱重新组合
    val reduceDS = mapDS.reduceByKeyAndWindow(
      (x: Int, y: Int) => x + y,
      Minutes(1),
      Seconds(10)
    )

    // TODO 1.统计完成后将结果直接保存在Mysql中
    //        数据库里的数据没有顺序
    //        大屏展示数据是，查询排序
    //      2.将排序后的数据保存到Mysql中
    //        将数据的结构进行转换，便于分组排序.
    val mapDS1 = reduceDS.map {
      case ((adid, time), sum) => {
        (adid, (time, sum))
      }
    }

    val groupDs = mapDS1.groupByKey()
    val resultDS = groupDs.mapValues(
      iter => {
        iter.toList.sortWith(
          (left, right) => {
            left._1 < right._1
          }
        )
      }
    )

    // 当使用窗口计算时
    // 1.判断数据计算的范围：（采集周期、多个采集周期）
    // 2.多长时间进行计算（滑动步长（默认是一个采集周期））
    // 3.窗口范围和步长大小（采集周期的整数倍）
    // 4.判断窗口范围和步长的关系
    //    窗口范围大，步长小，存在大量的重复数据，需要使用增量计算
    //    窗口范围大，步长也大，存在重复数据比较少，可以独立计算
    //    TODO 窗口一般大小是一个小时
    resultDS.print()

    ssc.start()
    ssc.awaitTermination()

  }

  case class AdClickData(ts: String, area: String, city: String, userId: String, adId: String)

}
