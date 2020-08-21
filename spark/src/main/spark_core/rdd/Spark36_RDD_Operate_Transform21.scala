package rdd

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark36_RDD_Operate_Transform21 {

  def main(args: Array[String]): Unit = {


    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)

    // TODO Spark  - 转换算子 - Key-Value类型

    val list = List(
      ("NBA","xxxxxx"),
      ("CBA","xxxxxx"),
      ("WNBA","xxxxxx"),
      ("NBA","yyyyy")
    )

    val rdd = sc.makeRDD(list,2)
    // TODO 对相同的key进行分组
    //  groupBy : 根据指定的规则进行分组，分组的key是通过计算活动
    //  groupByKey：根据数据的key值进行分组，相同的key对应的value放在同一组中.
    val groupByRDD: RDD[(String, Iterable[(String, String)])] = rdd.groupBy(_._1)
    groupByRDD.collect().foreach(println)

    val groupByKeyRDD: RDD[(String, Iterable[String])] = rdd.groupByKey()
    groupByKeyRDD.collect().foreach(println)

    sc.stop()


  }

}
