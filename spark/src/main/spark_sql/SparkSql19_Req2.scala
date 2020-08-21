import org.apache.spark.SparkConf
import org.apache.spark.sql._
import org.apache.spark.sql.expressions.Aggregator

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

object SparkSql19_Req2 {

  def main(args: Array[String]): Unit = {

    // TODO SparkSQL
    //  UDAF 强类型和DataSet结合使用

    System.setProperty("HADOOP_USER_NAME", "atguigu")
    val sqlConf = new SparkConf().setMaster("local[*]").setAppName("SQL")
    val spark: SparkSession = SparkSession.builder().enableHiveSupport().config(sqlConf).getOrCreate()

    // TODO 向Hive中建表插入数据
    //  Scala中如果写sql，使用双引号不方便，一般使用多行字符串。
    spark.sql("use spark_sql")

    // TODO 从Hive表中获取满足条件的数据

    spark.sql(
      """
        | select
        |        a.*,
        |        c.area,
        |        c.city_name,
        |        p.product_name
        | from user_visit_action a
        |      join city_info c on a.city_id = c.city_id
        |      join product_info p on a.click_product_id = p.product_id
        |      where a.click_product_id > -1
      """.stripMargin
    ).createOrReplaceTempView("t1")


    // TODO 将数据根据区域和商品进行分组，统计商品的点击数量
    val cityRemark = new CityRemarkUDAF()
    spark.udf.register("cityRemark", functions.udaf(cityRemark))
    spark.sql(
      """
        |select
        |      area,
        |      product_name,
        |      count(1) as click_cnt,
        |      cityRemark(city_name)
        |from t1 group by area,product_name
      """.stripMargin
    ).createOrReplaceTempView("t2")

    // TODO
    spark.sql(
      """
        |select
        |    *,
        |    rank() over(partition by area order by click_cnt desc)ran
        |from t2
      """.stripMargin
    ).createOrReplaceTempView("t3")

    // TODO
    spark.sql(
      """
        |select *
        |from
        |  t3
        |where ran <= 3
      """.stripMargin).show

    spark.stop()
  }

  // 自定义聚合函数，实现城市配注
  // 相同区域，产品的数据会进入到聚合函数，传递的值为城市名称
  // 计算城市点击数量占比:城市点击量/区域点击总量(所有城市)
  // 将计算结果进行排序取前2名和剩余
  // 使用强类型的聚合函数.

  case class Buff(var totalCnt: Long, var cityMap: mutable.Map[String, Long])

  // 1.继承类
  //    IN : String
  //    BUFF: CityMap[cityName,clickCnt],totalCnt
  //    OUT: Strinig.
  // 2.重写方法(6)
  class CityRemarkUDAF extends Aggregator[String, Buff, String] {
    // 初始化
    override def zero: Buff = {
      Buff(0L, mutable.Map[String, Long]())
    }

    // 更新缓冲区
    override def reduce(b: Buff, cityName: String): Buff = {
      // 更新点击数量的总和
      b.totalCnt += 1
      // 更新对应的城市点击量
      val newCount = b.cityMap.getOrElse(cityName, 0L) + 1
      b.cityMap.update(cityName, newCount)
      b
    }

    // 合并缓冲区
    override def merge(b1: Buff, b2: Buff): Buff = {
      // 合并点击数量的总和
      b1.totalCnt += b2.totalCnt
      // 合并城市的Map
      val map1 = b1.cityMap
      val map2 = b2.cityMap
      b1.cityMap = map1.foldLeft(map2) {
        case (map, (k, v)) => {
          val newValue = map.getOrElse(k, 0L) + v
          map.update(k, newValue)
          map
        }
      }
      b1
    }

    // 计算结果并返回
    override def finish(reduction: Buff): String = {
      val totalCnt = reduction.totalCnt
      val cityMap = reduction.cityMap

      // 将城市集合中的数据进行排序并取前两名
      val cityToCountList = cityMap.toList.sortWith(
        (left, right) => {
          left._2 > right._2
        }
      ).take(2)

      // 判断是否存在其他城市
      val hasRest = cityMap.size > 2

      val list = ListBuffer[String]()
      var sum = 0L
      cityToCountList.foreach {
        case (city, cnt) => {
          val r = cnt * 100 / totalCnt
          list.append(city + " " + r + "%")
          sum = sum + r
        }
      }

      if (hasRest){
        list.append("其他" + (100 - sum) + "%")
      }
      list.mkString(",")
    }

    override def bufferEncoder: Encoder[Buff] = Encoders.product

    override def outputEncoder: Encoder[String] = Encoders.STRING
  }

}
