import org.apache.spark.SparkConf
import org.apache.spark.sql._

object SparkSql16_Req_Mock {

  def main(args: Array[String]): Unit = {

    // TODO SparkSQL
    //  UDAF 强类型和DataSet结合使用

    System.setProperty("HADOOP_USER_NAME","atguigu")
    val sqlConf = new SparkConf().setMaster("local[*]").setAppName("SQL")
    val spark: SparkSession = SparkSession.builder().enableHiveSupport().config(sqlConf).getOrCreate()

    // TODO 向Hive中建表插入数据
    //  Scala中如果写sql，使用双引号不方便，一般使用多行字符串。
    spark.sql("use spark_sql")
    spark.sql(
      """
        |CREATE TABLE `user_visit_action`(
        |  `date` string,
        |  `user_id` bigint,
        |  `session_id` string,
        |  `page_id` bigint,
        |  `action_time` string,
        |  `search_keyword` string,
        |  `click_category_id` bigint,
        |  `click_product_id` bigint,
        |  `order_category_ids` string,
        |  `order_product_ids` string,
        |  `pay_category_ids` string,
        |  `pay_product_ids` string,
        |  `city_id` bigint)
        |row format delimited fields terminated by '\t';
      """.stripMargin
    )
    spark.sql(
      """
        |load data local inpath 'input/user_visit_action.txt' into table user_visit_action;
      """.stripMargin)
    spark.sql(
      """
        |CREATE TABLE `product_info`(
        |  `product_id` bigint,
        |  `product_name` string,
        |  `extend_info` string)
        |row format delimited fields terminated by '\t';
      """.stripMargin)
    spark.sql(
      """
        |load data local inpath 'input/product_info.txt' into table product_info;
      """.stripMargin)
    spark.sql(
      """
        |CREATE TABLE `city_info`(
        |  `city_id` bigint,
        |  `city_name` string,
        |  `area` string)
        |row format delimited fields terminated by '\t';
      """.stripMargin)
    spark.sql(
      """
        |load data local inpath 'input/city_info.txt' into table city_info;
      """.stripMargin)
    spark.sql(
      """
        |select * from city_info
      """.stripMargin).show(10)

    spark.stop()
  }

  /*
  select *
  from (
      select
    *,
    rank_over(partition by area order by click_cnt desc)ran
  from (
        select
      area,
      product_name,
      count(1) as click_cnt
    from(
        select
        a.*,
        c.area,
        c.city_name,
        p.product_name
      from user_visit_action a
      join city_info c on a.city_id = c.city_id
      join product_info p on a.click_product_id = p.product_id
      where a.click_product_id > -1
    )t1 group by area,product_name
  )t2
  )t3
    */


}
