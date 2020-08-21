import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, Dataset, Row, SparkSession}

object SparkSql02_DataFrame_DataSet {

  def main(args: Array[String]): Unit = {

    // TODO SparkSQL

    val sqlConf = new SparkConf().setMaster("local[*]").setAppName("SQL")
    val spark: SparkSession = SparkSession.builder().config(sqlConf).getOrCreate()
    import spark.implicits._

    val df: DataFrame = spark.read.json("input/user.json")


    // TODO DataFrame => DateSet
//    val ds: Dataset[User] = df.as[User]
//    val ds: Dataset[Emp] = df.as[Emp]
    val ds: Dataset[Emp] = df.toDF("age","username").as[Emp]
    ds.show

    // TODO 数据在转换成样例类时，属性缺失？
    //    会发生错误
    //  数据在转换成样例类时，属性名称不匹配
    //    会发生错误


    // TODO DateSet => DataFrame
    val empSeq: Seq[Emp] = Seq(Emp("zhangsan",30),Emp("lisi",40))
    val ds1: Dataset[Emp] = empSeq.toDS()
    ds1.show

    // TODO DataFrame 其实是一个类型的别名
    //  type DataFrame = Dataset[Row]
    //  其实是特定类型的DateSet
    val df1: DataFrame = ds1.toDF()
    df1.show

    // DataFrame => RDD => RDD[Row]




    spark.stop()

  }

  case class User(id:Long,name:String,age:Long)
  case class Emp(username:String,age:Long)

}
