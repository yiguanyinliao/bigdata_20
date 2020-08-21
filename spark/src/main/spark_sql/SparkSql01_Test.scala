import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, Dataset, Row, SparkSession}

object SparkSql01_Test {

  def main(args: Array[String]): Unit = {

    // TODO SparkSQL

    // SparkContext
    // 单例模式：构造方法私有化
    // 静态.
    /*
    class Test{
      private static Test test = new Test():
      private Test(){

      }
      public static Test getInstance(){

      }

    }
     */

    // TODO 创建SparkSession环境对象，类似于RDD中的SparkContext
    val sqlConf = new SparkConf().setMaster("local[*]").setAppName("SQL")
    // TODO builder =  封装了构建对象的一个完整过程（不只是new SparkSession）
    val sparkssss: SparkSession = SparkSession.builder().config(sqlConf).getOrCreate()
    // TODO 在导入spark隐式转换时，不是包名，是环境对象的变量引用名称
    //  变量名称必须用val修饰
    import sparkssss.implicits._

    // TODO 操作数据

    val df: DataFrame = sparkssss.read.json("input/user.json")
    df.show()

    // 将DF模型在处理时转换为临时视图使用
    df.createOrReplaceTempView("user")
    // 1.SQL
    sparkssss.sql("select * from user").show
    sparkssss.sql("select name from user").show
    sparkssss.sql("select avg(age) from user").show

    // 2.DSL
    df.select("name","age").show
    df.select("*").show
    // 如下写法确实无法直接使用，spark为了操作方便，需要引入隐式转换规则，
    // 将下面处理转换为符合spark的操作方式
    //     import spark.implicits._
    df.select($"age" + 1).show
    df.select('age + 1).show

    // 3.RDD、DataFrame、Dataset
    // 3.1 RDD - 关系数据本身
    val rdd: RDD[(Int, String, Int)] = sparkssss.sparkContext.makeRDD(
      List(
        (1, "zhangsan", 20),
        (2, "lisi", 30),
        (3, "wangwu", 40)
      )
    )

    // 3.2 DataFrame - 关系数据结构
    //  toDF方法等同于给结构起别名，列的数量应该保持一致
    val df1: DataFrame = rdd.toDF("id","name","age")
    df1.show
//    val df2: DataFrame = rdd.toDF("id","name")  (X)
//    df2.show
//    val df3: DataFrame = rdd.toDF("id","name","age","xxx")   (X)
//    df3.show
    val df4 = rdd.toDF()
    df4.show

    // TODO DateFrame => RDD
    //  DateFrame 转换为RDD时，处理的数据类型被固定转换为Row类型
    val newRDD: RDD[Row] = df1.rdd
    newRDD.foreach(
      row => {
        println(row(0))
//        println(row.get(0))
      }
    )

    // 3.3 DataSet - 关系数据类型
    val userRDD = rdd.map(
      t => {
        User(t._1, t._2, t._3)
      }
    )
    // TODO RDD => DataSet
    val userDS: Dataset[User] = userRDD.toDS()
    userDS.show

    val newRDD1: RDD[User] = userDS.rdd
    newRDD1.collect().foreach(println)


    sparkssss.stop()

  }

  case class User(id:Int,name:String,age:Int)

}
