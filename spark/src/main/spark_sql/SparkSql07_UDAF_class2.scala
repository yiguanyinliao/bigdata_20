import org.apache.spark.SparkConf
import org.apache.spark.sql._
import org.apache.spark.sql.expressions.Aggregator

object SparkSql07_UDAF_class2 {

  def main(args: Array[String]): Unit = {

    // TODO SparkSQL
    //  UDAF 强类型和DataSet结合使用

    val sqlConf = new SparkConf().setMaster("local[*]").setAppName("SQL")
    val spark: SparkSession = SparkSession.builder().config(sqlConf).getOrCreate()
    import spark.implicits._

    val df = spark.read.json("input/user.json")

    // Sql 不支持强类型的聚合函数
    // DataFrame（弱类型）DataSet（强类型）
    // UDAF(强类型） + DateSet  =>  DSL
    val ds = df.as[User]

    // 创建聚合函数
    val udaf = new MyAvgAgeUDAF_class
    // 因为聚合函数是强类型的，那么sql没有类型的概念，所以无法在sql中执行，可以采用DSL语法进行访问
    // 将强类型的聚合函数转换为可查询的列，那么就可以将一行的数据封装为对象传递给聚合函数。
    ds.select(udaf.toColumn).show


    spark.stop()
  }

  case class User(name:String,age:Long)
  case class Buff(var sum:Long,var cnt:Long)

  /**
    * 使用强类型的操作实现平均值的处理
    * 1.继承org.apache.spark.sql.expressions.Aggregator
    *   定义类型
    *   IN：输入数据的类型 User
    *   BUFF：缓冲区数据类型
    *   OUT：输出数据的类型 Double
    * 2.重写方法（6）
    */
  class MyAvgAgeUDAF_class extends Aggregator[User,Buff,Double] {
    override def zero: Buff = Buff(0L,0L)

    override def reduce(buffer: Buff,user: User): Buff = {
      buffer.sum += user.age
      buffer.cnt += 1
      buffer
    }

    override def merge(b1: Buff, b2: Buff): Buff = {
      b1.sum += b2.sum
      b1.cnt += b2.cnt
      b1
    }

    override def finish(reduction: Buff): Double = {
      reduction.sum/reduction.cnt
    }

    override def bufferEncoder: Encoder[Buff] = Encoders.product

    override def outputEncoder: Encoder[Double] = Encoders.scalaDouble
  }

}
