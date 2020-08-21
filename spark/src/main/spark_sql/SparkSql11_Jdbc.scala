import java.util.Properties

import org.apache.spark.SparkConf
import org.apache.spark.sql._

object SparkSql11_Jdbc {

  def main(args: Array[String]): Unit = {

    // TODO SparkSQL
    //  UDAF 强类型和DataSet结合使用

    val sqlConf = new SparkConf().setMaster("local[*]").setAppName("SQL")
    val spark: SparkSession = SparkSession.builder().config(sqlConf).getOrCreate()
    import spark.implicits._

    // TODO 从JDBC中读取数据
    //  注意：本地的mysql版本是8.0以上的，使用5.0版本的jar包会报连接异常错误，需要把mysql连接的jar包更新成8.0版本以上的.
    val df: DataFrame = spark.read.format("jdbc")
      .option("url", "jdbc:mysql://localhost:3306/hive_test")
      .option("driver", "com.mysql.jdbc.Driver")
      .option("user", "root")
      .option("password", "hgc123465")
      .option("dbtable", "course")
      .load()
    df.show

//    spark.read.format("jdbc")
//      .options(Map("url"->"jdbc:mysql://localhost:3306/hive_test?user=root&password=hgc123465",
//        "dbtable"->"course","driver"->"com.mysql.jdbc.Driver")).load().show


//    val props: Properties = new Properties()
//    props.setProperty("user", "root")
//    props.setProperty("password", "hgc123465")
//    val df: DataFrame = spark.read.jdbc("jdbc:mysql://localhost:3306/hive_test", "course", props)
//    df.show

    spark.stop()
  }


}
