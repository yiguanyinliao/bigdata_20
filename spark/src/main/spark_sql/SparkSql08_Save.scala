import org.apache.spark.SparkConf
import org.apache.spark.sql._
import org.apache.spark.sql.expressions.Aggregator

object SparkSql08_Save {

  def main(args: Array[String]): Unit = {

    // TODO SparkSQL
    //  UDAF 强类型和DataSet结合使用

    val sqlConf = new SparkConf().setMaster("local[*]").setAppName("SQL")
    val spark: SparkSession = SparkSession.builder().config(sqlConf).getOrCreate()
    import spark.implicits._

    val df = spark.read.json("input/user.json")

    // TODO 将数据保存到指定的位置
    //  SparkSQL 通用的读取和保存的文件格式默认是parquet格式
    //  可以使用format改变需要保存的格式
    //  默认情况下，保存文件的目录不能重复
    //  如果想要在同一个输出路径中生成文件，需要采用特定的保存模式mode()
    //  mode()有 error(default)、append、overwrite、ignore几种模式

    df.write.save("output")
    df.write.format("json").save("output1")
    df.write.format("json").mode("append").save("output1")

    spark.stop()
  }


}
