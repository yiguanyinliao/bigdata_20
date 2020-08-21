package Req

import java.sql.{DriverManager, ResultSet}
import java.text.SimpleDateFormat

import org.apache.kafka.clients.consumer.{ConsumerConfig, ConsumerRecord}
import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, InputDStream}
import org.apache.spark.streaming.kafka010.{ConsumerStrategies, KafkaUtils, LocationStrategies}
import org.apache.spark.streaming.{Seconds, StreamingContext}

import scala.collection.mutable.ListBuffer

object Req1_BlackList1 {


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

    // TODO 2.周期性读取用户黑名单数据，将黑名单数据中的用户的数据过滤掉
    //        对正常广告点击的数据进行统计

    val aggregateDStream = clickDStream.transform(
      rdd => {
        // TODO 读取Mysql数据，获取黑名单信息
        val driverClass = "com.mysql.jdbc.Driver"
        val url = "jdbc:mysql://localhost:3306/spark0317"
        val user = "root"
        val password = "hgc123465"
        Class.forName(driverClass)
        val conn = DriverManager.getConnection(url, user, password)
        val pstat = conn.prepareStatement(
          """
            |select userid from black_list
          """.stripMargin
        )

        val rs: ResultSet = pstat.executeQuery()
        val blackids = ListBuffer[String]()

        while (rs.next()) {
          blackids.append(rs.getString(1))
        }
        rs.close()
        pstat.close()
        conn.close()

        // TODO 对采集的数据进行过滤，将黑名单中的数据过滤掉
        val filterRDD = rdd.filter(
          data => {
            !blackids.contains(data.userId)
          }
        )

        // TODO 对过滤后的数据进行统计
        //  (word,count) => (day-userId-adID,count) => (day-userId-adID,sum)。
        val sdf = new SimpleDateFormat("yyyy-MM-dd")
        filterRDD.map(
          data => {
            // TODO 统计是基于日期进行，所以需要将数据格式转换为以天为单位
            // TS => Date
            val date = new java.util.Date(data.ts.toLong)
            ((sdf.format(date), data.userId, data.adId), 1)
          }
        ).reduceByKey(_ + _)

      }
    )

    // TODO 3.将统计结果和用户的数据进行合并
    //        对结果进行判断，如果结果超过指定的阈值，将这个用户拉入黑名单
    aggregateDStream.foreachRDD(
      rdd => {
        rdd.foreach {
          case ((day, userid, adid), sum) => {
            println(s"处理的数据为：（$day,$userid,$adid,$sum）")
            val driverClass = "com.mysql.jdbc.Driver"
            val url = "jdbc:mysql://localhost:3306/spark0317"
            val user = "root"
            val password = "hgc123465"
            Class.forName(driverClass)
            val conn = DriverManager.getConnection(url, user, password)

            // TODO 更新用户点击统计表

            val pstat = conn.prepareStatement(
              """
                |insert into user_ad_count(dt,userid,adid,count)
                |values(?,?,?,?)
                |on duplicate key
                |update count = count + ?
              """.stripMargin)

            // TODO 判断是否超过阈值，加入黑名单
            val pstat1 = conn.prepareStatement(
              """
                |select useid from user_ad_count where dt = ? and userid = ? and adid = ? and count >= 100
              """.stripMargin)


            val pstat2 = conn.prepareStatement(
              """
                |insert into black_list(userid)
                |values(?)
                |on duplicate key update userid = ?
                |
              """.stripMargin)

            pstat.setString(1,day)
            pstat.setString(2,userid)
            pstat.setString(3,adid)
            pstat.setString(4,sum.toString)
            pstat.setString(5,sum.toString)
            pstat.executeUpdate()


            pstat1.setString(1,day)
            pstat1.setString(2,userid)
            pstat1.setString(3,adid)
            val rs: ResultSet = pstat1.executeQuery()
            if (rs.next()) {
              pstat2.setString(1,userid)
              pstat2.setString(2,userid)
              pstat2.executeUpdate()
            }
            rs.close()
            pstat.close()
            pstat1.close()
            pstat2.close()
            conn.close()
          }
        }
      }
    )
    ssc.start()
    ssc.awaitTermination()
  }

  case class AdClickData(ts: String, area: String, city: String, userId: String, adId: String)

}