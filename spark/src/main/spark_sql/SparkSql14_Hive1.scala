import org.apache.spark.SparkConf
import org.apache.spark.sql._

object SparkSql14_Hive1 {

  def main(args: Array[String]): Unit = {

    // TODO SparkSQL
    //  UDAF 强类型和DataSet结合使用

    val sqlConf = new SparkConf().setMaster("local[*]").setAppName("SQL")
    val spark: SparkSession = SparkSession.builder().enableHiveSupport().config(sqlConf).getOrCreate()


    // TODO 访问外置Hive
    // 1.添加依赖
    // 2.在SparkSeesion中增加enableHiveSupport()
    // 3.将Hive-site.xml 拷贝到当前项目classpath中
    // 4.引入mysql驱动的依赖

    // 出现了没有找到表的异常
    // 原因：拷贝的hive-site.xml文件没有编译到classpath文件下

    spark.sql("use spark_sql").show
    spark.sql("show tables").show
    spark.sql("select * from city_info").show
//    spark.sql("select * from userid").show

    spark.stop()
  }


}
