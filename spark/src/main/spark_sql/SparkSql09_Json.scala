import org.apache.spark.SparkConf
import org.apache.spark.sql._

object SparkSql09_Json {

  def main(args: Array[String]): Unit = {

    // TODO SparkSQL
    //  UDAF 强类型和DataSet结合使用

    val sqlConf = new SparkConf().setMaster("local[*]").setAppName("SQL")
    val spark: SparkSession = SparkSession.builder().config(sqlConf).getOrCreate()

    val df = spark.read.json("input/user.json")
    val df1 = spark.read.json("input/user1.json")

    df.show()
    df1.show()

    // SparkSQL中JSON读取依赖于底层的Hadoop操作
    // Hadoop读取文件时采用的是一行一行的方式读取
    // 读取一行的数据有可能是不完整的，所以无法执行进行分析
    // Spark要求读取的JSON数据必须每一行是符合JSON的格式要求。

    spark.stop()
  }


}
