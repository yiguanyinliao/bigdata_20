import org.apache.spark.SparkConf
import org.apache.spark.sql._

object SparkSql10_Csv {

  def main(args: Array[String]): Unit = {

    // TODO SparkSQL
    //  UDAF 强类型和DataSet结合使用

    val sqlConf = new SparkConf().setMaster("local[*]").setAppName("SQL")
    val spark: SparkSession = SparkSession.builder().config(sqlConf).getOrCreate()

    val df: DataFrame = spark.read.format("csv")
      .option("sep", ";")
      .option("inferSchema", "true")
      .option("header", "true")
      .load("input/people.csv")

    df.show




    spark.stop()
  }


}
