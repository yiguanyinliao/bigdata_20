import org.apache.spark.SparkConf
import org.apache.spark.sql._

object SparkSql18_Req1 {

  def main(args: Array[String]): Unit = {

    // TODO SparkSQL
    //  UDAF 强类型和DataSet结合使用

    System.setProperty("HADOOP_USER_NAME","atguigu")
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
    spark.sql(
      """
        |select
        |      area,
        |      product_name,
        |      count(1) as click_cnt
        |from t1 group by area,product_name
      """.stripMargin
    ).createOrReplaceTempView("t2")

    // TODO  将统计结果根据点击量排序（降序）
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


}
