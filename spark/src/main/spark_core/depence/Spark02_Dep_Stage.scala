package depence

import org.apache.spark.{SparkConf, SparkContext}

object Spark02_Dep_Stage {

  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)

    // TODO RDD中阶段的划分
    //  DAGScheduler。handleJobSubmitted。
    //  有向无环图调度器对象中完成了整个作业的阶段的划分
    //  前面的阶段不执行完，是不能执行后面阶段的
    //  Spark 阶段划分时，类型只有2种：ResultStage、ShuffleMapStage



    // finalStage = createResultStage(finalRDD, func, partitions, jobId, callSite)
    //  1.如果执行过程中，没有Shuffle操作，至少会有一个stage（阶段）：ResultStage
    //      new ResultStage
    //  2.创建JobSubmitted之前，执行了
    //      val parents = getOrCreateParentStages(rdd, jobId)
    //  3.获取Shuffle依赖（宽依赖）集合后进行了转换 shuffleDep => ShuffleMapStage
    //       /*
    //        getShuffleDependencies(rdd).map { shuffleDep =>
    //           getOrCreateShuffleMapStage(shuffleDep, firstJobId)
    //          }.toList
    //      */
    //  4.如何获取当前RDD的依赖关系（宽依赖）
        /*

          private[scheduler] def getShuffleDependencies(
              rdd: RDD[_]): HashSet[ShuffleDependency[_, _, _]] = {
            val parents = new HashSet[ShuffleDependency[_, _, _]]   // 准备集合
            val visited = new HashSet[RDD[_]]
            val waitingForVisit = new ListBuffer[RDD[_]]
            waitingForVisit += rdd
            while (waitingForVisit.nonEmpty) {
              val toVisit = waitingForVisit.remove(0)
              if (!visited(toVisit)) {
                visited += toVisit
                toVisit.dependencies.foreach {
                  case shuffleDep: ShuffleDependency[_, _, _] =>  // 当前RDD的依赖关系中，如果有宽依赖（Shuffle依赖），
                                                                       会将shuffleDep => ShuffleMapStage形成新的阶段
                    parents += shuffleDep
                  case dependency =>
                    waitingForVisit.prepend(dependency.rdd)
                }
              }
            }
            parents
          }

        */


    // TODO Spark作业中阶段的数量等于：1 + shuffle依赖的个数
    //  只要存在Shuffle，就会形成stage（阶段）。
    sc.stop()

  }



}
