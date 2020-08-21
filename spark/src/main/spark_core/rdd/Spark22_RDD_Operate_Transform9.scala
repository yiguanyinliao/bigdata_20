package rdd

import org.apache.spark.{SparkConf, SparkContext}

object Spark22_RDD_Operate_Transform9 {

  def main(args: Array[String]): Unit = {


    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)

    // TODO Group by
    var list = List(1,2,3,4,6,5)
    val rdd = sc.makeRDD(list,2)

    // 根据指定的规则对数据进行分组
    // TODO group by 算子会将RDD分区中的数据打乱重新组合
    //  这种操作称为shuffle
    //  即使打乱重新组合了，那么数据依然可能全部都放置在一个分区中，不一定平均分
    val newRdd = rdd.groupBy(num => num % 2)
    newRdd.saveAsTextFile("output")

    // TODO  默认情况下，RDD的转换操作，分区数量应该是相同的，如果想改变，也是可以的
    //  改变分区是，group by 匿名函数的类型不能省略
    //  一个组的数据在一个分区中，但并不一定一个分区中只有一个组。
    val newRdd1 = rdd.groupBy(
      (x: Int) => x % 3, 2
//      (x: Int) => x % 3, 3
    )
    newRdd1.saveAsTextFile("output1")

    sc.stop()

  }

}
