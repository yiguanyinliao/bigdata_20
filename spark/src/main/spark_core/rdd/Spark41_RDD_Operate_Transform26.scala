package rdd

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark41_RDD_Operate_Transform26 {

  def main(args: Array[String]): Unit = {


    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)

    // TODO Spark  - 转换算子 - Key-Value类型
    //  思考一个问题：reduceByKey、foldByKey、aggregateByKey、combineByKey的区别？
    //  底层调用的代码逻辑很相似，只是传的参数不一样，都有一个预聚合

    val list = List(
      ("a", 88), ("b", 95), ("a", 91),
     ("b", 93), ("a", 95), ("b", 98)
    )
    val rdd = sc.makeRDD(list)

/*
combineByKeyWithClassTag
    createCombiner: V => C,         ==> (v:V) =>v
    mergeValue: (C, V) => C,        ==> (v,V) =>V
    mergeCombiners: (C, C) => C,    ==> (v,V) =>V
    partitioner: Partitioner,
    mapSideCombine: Boolean = true,
    serializer: Serializer = null
*/
//    rdd.reduceByKey()
//    rdd.aggregateByKey()
//    rdd.foldByKey()
//    rdd.combineByKey()




    sc.stop()


  }

}
