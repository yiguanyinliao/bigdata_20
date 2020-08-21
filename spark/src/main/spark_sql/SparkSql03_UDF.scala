import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, Dataset, SparkSession}

object SparkSql03_UDF {

  def main(args: Array[String]): Unit = {

    // TODO SparkSQL

    val sqlConf = new SparkConf().setMaster("local[*]").setAppName("SQL")
    val spark: SparkSession = SparkSession.builder().config(sqlConf).getOrCreate()
    import spark.implicits._


    // TODO UDF: 自定义函数
    val df: DataFrame = spark.read.json("input/user.json")
    df.createOrReplaceTempView("user")

    // sparkSql 允许自定义函数，对数据进行处理
    // 将自定义函数注册到SparkSql中

    spark.udf.register("addName",(name:String)=>{
      "Name:" + name
    })

    spark.sql("select name,age from user").show
//    spark.sql("select avg(age) from user").show
    // 在sql中使用自定义函数
    spark.sql("select addName(name),age from user").show

    spark.stop()

  }
  case class User(id:Long,name:String,age:Long)
  case class Emp(username:String,age:Long)

}
