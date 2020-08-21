import org.apache.spark.SparkConf
import org.apache.spark.sql._
import org.apache.spark.sql.expressions.Aggregator

object SparkSql06_UDAF_class1 {

  def main(args: Array[String]): Unit = {

    // TODO SparkSQL

    val sqlConf = new SparkConf().setMaster("local[*]").setAppName("SQL")
    val spark: SparkSession = SparkSession.builder().config(sqlConf).getOrCreate()

    // MyAvgAgeUDAF ：弱类型的用户自定义聚合函数UserDefinedAggregateFunction
    //    版本：spark 3.0之前
    //    功能：重写8个方法
    //    原理：通过缓冲区对数据迭代处理，最终获取结果
    // MyAvgAgeUDAF_class：强类型的用户自定义聚合函数 Aggregator
    //    版本：spark 3.0之后
    //        早起版本存在强类型聚合函数，但不能应用于sql操作。
    //    功能：重写8个方法，不包括弱类型中结构的声明（3）和稳定性的方法（1）
    //          增加了类型操作时的编码处理（2）
    //    原理：通过缓冲区对数据迭代处理，最终获取结果

    spark.stop()
  }

  case class Buff(var sum:Long,var cnt:Long)
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
