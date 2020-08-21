import org.apache.spark.SparkConf
import org.apache.spark.sql._

object SparkSql12_Jdbc_Save {

  def main(args: Array[String]): Unit = {

    // TODO SparkSQL
    //  UDAF 强类型和DataSet结合使用

    val sqlConf = new SparkConf().setMaster("local[*]").setAppName("SQL")
    val spark: SparkSession = SparkSession.builder().config(sqlConf).getOrCreate()
    import spark.implicits._

    // TODO 从JDBC中读取数据

    val rdd = spark.sparkContext.makeRDD(
      List(
        (1,"zhangsan",30),(2,"lisi",40)
      )
    )

    val df = rdd.toDF("id","name","age")

    df.write.format("jdbc")
      .option("url", "jdbc:mysql://localhost:3306/hive_test")
      .option("driver", "com.mysql.jdbc.Driver")
      .option("user", "root")
      .option("password", "hgc123465")
      .option("dbtable", "course1")
      .mode("append")
//      .mode(SaveMode.Append)
      .save()

    spark.stop()
  }


}
