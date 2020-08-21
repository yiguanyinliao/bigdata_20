package rdd

import org.apache.spark.{SparkConf, SparkContext}

object Spark31_RDD_Operate_Transform16 {

  def main(args: Array[String]): Unit = {


    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)

    // TODO Spark  - 转换算子 - sortBy

    val list = List(1,3,12,2,5,4,7)
//    list.sortBy(num => num)(Ordering.Int.reverse)
    val rdd = sc.makeRDD(list,2)

    // TODO 根据指定的规则对处理的数据进行排序
    //  sortBy的第二个参数表示排序的方式
    //  默认的排序为升序，取值为true，设置为false，那么降序
    //  执行结果：全局排序
    //  如果分区内的数据是否会排序
    //  数据在排序的过程中会将数据打乱重新组合，所以存在shuffle过程。
    rdd.saveAsTextFile("output1")
    rdd.sortBy(num => num).saveAsTextFile("output")
    val newRdd = rdd.sortBy(num => num)
    val newRdd1 = rdd.sortBy(num => num,false)
    newRdd.collect().foreach(println)
    newRdd1.collect().foreach(println)

    sc.stop()

  }

}
