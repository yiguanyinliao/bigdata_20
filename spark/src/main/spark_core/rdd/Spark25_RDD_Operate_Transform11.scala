package rdd

import org.apache.spark.{SparkConf, SparkContext}

object Spark25_RDD_Operate_Transform11 {

  def main(args: Array[String]): Unit = {


    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)

    // TODO sample （在大数据领域中主要用于数据倾斜的分析，一般用于机器学习从测试样本获取）
    //  采样，从需要处理的数据中进行抽取采样，进行数据分析
    //  对每一个需要处理的数据进行抽取操作
    //  1.从数据集中抽取多少数据？
    //  2.抽取的数据是否有规律？
    //  3.抽取完数据是否放回到数据集中
    //    3.1 抽取放回
    //    3.2 抽取不放回.

    val list = List(1,2,3,4,5,6,7,8)
    val rdd = sc.makeRDD(list)

    // TODO sample 算子有3个参数：
    //  1.withReplacement 抽取数据后是否放回，true表示放回
    //  2.fraction
    //    抽取不放回的场景下下，每条数据的抽取几率（0-1）
    //    这里的数值表示抽取的几率，而不是抽取的比例
    //    抽取放回的场景下，表示每条数据的抽取次数（预期） （大于0）.
    //  3.seed
    //  抽取不放回的场景下下，抽取数据的随机数种子
    //  抽取放回的场景下下，抽取数据的随机种子，用于次数的计算。
//    val newRdd = rdd.sample(false,0.5)
    val newRdd = rdd.sample(false,0.5, 13)
    newRdd.collect().foreach(println)

    println("===========================================")
    val newRdd1 = rdd.sample(true,3)
//    val newRdd1 = rdd.sample(true,3, 13)
    newRdd1.collect().foreach(println)

    // 随机数不随机，随机数的底层施工的是随机算法
    // Hash算法，参数（IN）=> 算法（Hash）=> 结果（OUT)
    // 随机数的种子确定，随机数既可以确定


    sc.stop()

  }

}
