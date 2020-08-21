package action_rdd

import org.apache.spark.{SparkConf, SparkContext}

object Spark02_RDD_Operate_Action1 {

  def main(args: Array[String]): Unit = {


    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)

    // TODO Spark - 行动算子 - reduce

    val list = List(1,2,3,4)
    val rdd = sc.makeRDD(list)

    val i: Int = rdd.reduce(_+_)
    val d: Double = rdd.sum() // 两种类型不一样，都是做的求和逻辑操作
    println("i = " + i)
    println("d = " + d)

    // TODO Spark - 行动算子 - collect
    //  collect 算子会将exeutor的执行结果以数组的方式返回到Driver端进行后续处理
    //  将exeutor端的数据汇集到Driver端是，有可能内存不够用，发生错误
    //  弊端：1.一次collect会导致一次Shuffle，一次Shuffle调度一次stage，一次stage包含很多已分解的任务碎片Task
    //      属于比较耗时的操作
    //      2.一次collect操作会将分布式各个节点上的数据汇聚到一个driver节点上，后续的执行和运算会脱离分布式环境
    //      相当于在单机环境下运行，与Spark的分布式理念不合
    //      3.有可能会导致内存溢出
    //  如何规避：
    //          1.如果只需要变量RDD中的元素，大可不必使用collect，可以使用foreach语句
    //          2.如果要打印RDD中的元素，可用take语句，返回数据集前n个元素 data.take(1000).foreach(println)
    //          3.如果需要查看其中内容，可用saveAsTextFile方法
    //          4.单机环境下使用collect问题不大
    //  补充：collectPartitions：也是属于行动算子的一种，同样会将数据汇集到Driver节点上
    //        和collect区别是，collect是将所有RDD汇集到一个数组里，而collectPartitions是将各个分区内所有元素
    //        存储到一个数组里，再将这些数组汇集到driver端产生一个数组；collect产生一维数组，
    //        而collectPartitions产生二维数组。



    rdd.collect()

    // TODO Spark - 行动算子 - count
    //  获取RDD的元素数量.
    val l: Long = rdd.count()
    println(l)


    // TODO Spark - 行动算子 - first
    //  获取RDD的第一个元素

    val ii: Int = rdd.first()
    println(ii)

    // TODO Spark - 行动算子 - take
    //  返回一个由RDD的前n个元素组成的数组。
    val ints: Array[Int] = rdd.take(3)
    println(ints.mkString(","))


    // TODO Spark - 行动算子 - takeOrdered
    //  先排序再返回数据.
    val list1 = List(4,5,6,1,3,2)
    val rdd1 = sc.makeRDD(list1)
    val intss = rdd1.takeOrdered(3)
    println(intss.mkString(","))




    sc.stop()


  }


}
