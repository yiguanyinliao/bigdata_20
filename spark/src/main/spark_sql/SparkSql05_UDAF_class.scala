import org.apache.spark.SparkConf
import org.apache.spark.sql.expressions.Aggregator
import org.apache.spark.sql._

object SparkSql05_UDAF_class {

  def main(args: Array[String]): Unit = {

    // TODO SparkSQL
    //  UDAF 使用弱类型的聚合函数。

    val sqlConf = new SparkConf().setMaster("local[*]").setAppName("SQL")
    val spark: SparkSession = SparkSession.builder().config(sqlConf).getOrCreate()
    import spark.implicits._

    // TODO UDAF: 自定义聚合函数
    //  自定义聚合函数应该有缓冲区。
    val df: DataFrame = spark.read.json("input/user.json")
    df.createOrReplaceTempView("user")

    // TODO 将强类型的聚合函数转换为弱类型的聚合函数
    spark.udf.register("avgAge", functions.udaf(new MyAvgAgeUDAF()))

    spark.sql("select avgAge(age) from user").show

    df.select("age").show
    df.select(functions.avg("age")).show // TODO DSL使用聚合函数

    spark.stop()
  }

  // 定义 缓冲区类型
  case class Buff(var sum:Long,var cnt:Long)
  // 自定义强类型聚合函数
  // 1.继承sql.expressions.Aggregator
  //    定义泛型
  //    IN：Long
  //    BUFF：Buff
  //    OUT：Double
  // 6.重新方法（6）
  class MyAvgAgeUDAF extends Aggregator[Long,Buff,Double] {
    // 缓存区初始化
    override def zero: Buff = Buff(0,0)

    // 聚合数据
    override def reduce(buff: Buff, input: Long): Buff = {
      buff.sum = buff.sum + input
      buff.cnt = buff.cnt + 1
      buff

    }

    // 多缓冲区的数据合并
    override def merge(buff1: Buff, buff2: Buff): Buff = {
      buff1.sum = buff1.sum + buff2.sum
      buff1.cnt = buff1.cnt + buff2.cnt
      buff1

    }

    // 完成聚合函数（计算）
    override def finish(buff: Buff): Double = {
      buff.sum.toDouble/buff.cnt
    }


    // SparkSQL 使用编码器对象进行序列化操作
    override def bufferEncoder: Encoder[Buff] = Encoders.product

    override def outputEncoder: Encoder[Double] = Encoders.scalaDouble
  }

}
