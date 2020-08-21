package rdd

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark07_RDD_Par4 {

  def main(args: Array[String]): Unit = {


    //    val sparkConf = new SparkConf().setMaster("local[2]").setAppName("RDD")
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)


    // TODO 在加载数据时，可以设定并行度来设置分区数量
    val list = List(1, 2, 3, 4,5)

//    val rdd1 = sc.makeRDD(list, 2)
//    val rdd2 = sc.makeRDD(list, 4)

    // 1.val array = seq.toArray
    // 2.positions(array.length,numSize)
    //   postions(5,3)
    /*
        def positions(length: Long, numSlices: Int): Iterator[(Int, Int)] = {    // TODO 底层代码逻辑
      (0 until numSlices).iterator.map { i =>
        val start = ((i * length) / numSlices).toInt
        val end = (((i + 1) * length) / numSlices).toInt
        (start, end)
      }
     */
    //   [0,1,2] => [(0,1),(1,3),(3,5)]
    // 3.array.slice(start,end) // 切分
    //    [(0,1),(1,3),(3,5)]=>Seq
    //    (1),(2,3),(4,5)
    val rdd3 = sc.makeRDD(list, 3)



//    rdd1.saveAsTextFile("output1")
//    rdd2.saveAsTextFile("output2")
    rdd3.saveAsTextFile("output3")


    sc.stop()

  }

}
