import org.apache.spark.SparkConf
import org.apache.spark.sql._

object SparkSql13_Hive {

  def main(args: Array[String]): Unit = {

    // TODO SparkSQL
    //  UDAF 强类型和DataSet结合使用

    val sqlConf = new SparkConf().setMaster("local[*]").setAppName("SQL")
    val spark: SparkSession = SparkSession.builder().enableHiveSupport().config(sqlConf).getOrCreate()
    import spark.implicits._


    // TODO 访问内置Hive
    //  出现了异常，增加enableHiveSupport()操作

    // 1.添加依赖
    // 2.在SparkSeesion中增加enableHiveSupport()。

    spark.sql("create table a_t(id int)")
    spark.sql("show tables").show

    spark.sql("load data local inpath'input/id.txt' into table a_t")

    val df: DataFrame = spark.sql("select * from a_t")
    df.show

    spark.stop()
  }


}
