package task

import org.apache.spark.{SparkConf, SparkContext}

object Spark01_Task {

  def main(args: Array[String]): Unit = {


    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)

    // TODO Spark - 任务（Task）
    //  Spark中为了并行计算，提供了分区的概念
    //  可以将数据在执行过程根据分区发给不同的计算节点来进行计算
    //  在发生数据给计算节点时，不应该仅仅是数据本身，还应该包含计算逻辑、血缘关系
    //  所以在传递数据准备计算时，需要发送大量内容给Executor
    //  所谓的任务其实就是对传输数据的一种封装
    //  Task(data,logic,dep,setting)。



    sc.stop()


  }


}
