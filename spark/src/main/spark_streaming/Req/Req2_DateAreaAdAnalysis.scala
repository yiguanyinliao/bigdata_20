package Req

import java.sql.{Connection, ResultSet}
import java.text.SimpleDateFormat

import org.apache.kafka.clients.consumer.{ConsumerConfig, ConsumerRecord}
import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, InputDStream}
import org.apache.spark.streaming.kafka010.{ConsumerStrategies, KafkaUtils, LocationStrategies}
import org.apache.spark.streaming.{Seconds, StreamingContext}

import scala.collection.mutable.ListBuffer

object Req2_DateAreaAdAnalysis {


  def main(args: Array[String]): Unit = {

    // TODO 需求：将每天对某个广告点击超过100次的用户拉黑

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("BlackList")
    val ssc = new StreamingContext(sparkConf, Seconds(3))

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


    val sdf = new SimpleDateFormat("yyyy-MM-dd")
    val mapDStream = clickDStream.map(

      data => {
        val day = sdf.format(new java.util.Date(data.ts.toLong))
        ((day,data.area,data.city,data.adId),1)
      }
    )

    val reduceDS = mapDStream.reduceByKey(_+_)

    // TODO 将统计结果保存到Mysql中
    reduceDS.foreachRDD(
      rdd => {
        rdd.foreachPartition(
          datas => {
            // TODO 获取连接对象
            val conn = JDBCUtil.getConnection
            // 预编译
            val pstat = conn.prepareStatement(
              """
                |insert into area_city_ad_count(dt,area,city,adid,count)
                |values(?,?,?,?,?)
                |on duplicate key
                |update count = count + ?
              """.stripMargin)
            datas.foreach{
              case ((dt,area,city,adid),count) =>{
                pstat.setString(1,dt)
                pstat.setString(2,area)
                pstat.setString(3,city)
                pstat.setString(4,adid)
                pstat.setString(5,count.toString)
                pstat.setString(6,count.toString)
                pstat.executeUpdate()

              }
            }
            pstat.close()
            conn.close()
          }
        )

        }
    )

    ssc.start()
    ssc.awaitTermination()

  }

  case class AdClickData(ts: String, area: String, city: String, userId: String, adId: String)

}
