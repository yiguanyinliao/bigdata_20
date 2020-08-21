import org.apache.spark.SparkConf
import org.apache.spark.sql.expressions.{MutableAggregationBuffer, UserDefinedAggregateFunction}
import org.apache.spark.sql.types.{DataType, LongType, StructField, StructType}
import org.apache.spark.sql.{DataFrame, Row, SparkSession}

object SparkSql04_UDAF {

  def main(args: Array[String]): Unit = {

    // TODO SparkSQL

    val sqlConf = new SparkConf().setMaster("local[*]").setAppName("SQL")
    val spark: SparkSession = SparkSession.builder().config(sqlConf).getOrCreate()

    // 所以的聚合操作，都应有缓冲区的概念
    val rdd = spark.sparkContext.makeRDD(
      List(1, 2, 3, 4)
    )

    // 累加器
    //    var t = (0,0)
    //
    //    rdd.foreach(
    //      num => {
    //        (t._1 + num, t._2 + 1)
    //      }
    //    )
    //
    //    println(t._1/t._2)

    // TODO UDAF: 自定义聚合函数
    //  自定义聚合函数应该有缓冲区。
    val df: DataFrame = spark.read.json("input/user.json")
    df.createOrReplaceTempView("user")

    spark.udf.register("avgAge", new MyAvgAgeUDAF)

    spark.sql("select avgAge(age) from user").show

//    spark.sql("select avg(age) from user").show

    spark.stop()
  }

  // 自定义聚合函数
  // 1.继承UserDefinedAggregateFunction
  // 2.重写方法(8)

  // a.使用弱类型数据操作，那么需要依靠顺序进行操作，所以容易出现错误
  // b. UserDefinedAggregateFunction 不推荐使用，因为是弱类型的聚合函数
  //    在DateSet中共无法使用，因为DataSet是带类型的
  // 使用spark3.0版本中强类型操作的udaf
  class MyAvgAgeUDAF extends UserDefinedAggregateFunction {
    // 输入的数据结构：Age
    override def inputSchema: StructType = {
      StructType(Array(StructField("age", LongType)))
    }

    // 缓冲区数据的结构:(Sum,Count)
    override def bufferSchema: StructType = {
      StructType(Array(
        StructField("totalAge", LongType),
        StructField("ageCount", LongType)
      ))

    }

    // 数据类型:计算结果的类型
    override def dataType: DataType = LongType

    // 函数稳定性
    override def deterministic: Boolean = true

    // 缓冲区初始化操作
    override def initialize(buffer: MutableAggregationBuffer): Unit = {
      buffer.update(0, 0L)
      buffer.update(1, 0L)
    }

    // 根据输入数据更新缓冲区
    override def update(buffer: MutableAggregationBuffer, input: Row): Unit = {

      val newSum = buffer.getLong(0) +  input.getLong(0)
      val newCount = buffer.getLong(1) + 1

      buffer.update(0, newSum)
      buffer.update(1, newCount)
    }

    // 多个缓冲区的合并操作
    override def merge(buffer1: MutableAggregationBuffer, buffer2: Row): Unit = {
      val newSum = buffer1.getLong(0) +  buffer2.getLong(0)
      val newCount = buffer1.getLong(1) + buffer2.getLong(1)

      buffer1.update(0, newSum)
      buffer1.update(1, newCount)
    }

    //计算结果
    override def evaluate(buffer: Row): Any = {
      buffer.getLong(0)/buffer.getLong(1)
    }
  }

}
