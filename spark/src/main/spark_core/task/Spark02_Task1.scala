package task

import org.apache.spark.{SparkConf, SparkContext}

object Spark02_Task1 {

  def main(args: Array[String]): Unit = {


    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)

    // TODO Spark - 任务（Task）
    //  Application
    //    执行main方法，等同于执行一个应用程序，所以存在Application的概念
    //  Job   Application => N*Job
    //    执行行动算子，会产生一个Job：ActionJob
    //  Stage   Job => N*Stage
    //    Spark执行Job时，会将执行过程划分不同的阶段
    //      这里的阶段划分需要对Shuffle依赖进行判断
    //      Spark 阶段划分时，类型只有2种：ResultStage、ShuffleMapStage
    //      在阶段的划分过程中最后一个阶段为ResultStage，其他的阶段都是ShuffleMapStage.
    //  Task    Stage => N*Task
    //    一个阶段中，会将最后一个RDD的分区转换为任务.



    sc.stop()


  }


}
