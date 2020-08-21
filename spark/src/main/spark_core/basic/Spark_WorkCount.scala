package basic

import autumn_frameworker.core.TApplication

object Spark_WorkCount extends App with TApplication{

  execute {

    // 创建控制器，执行调度
    val controller = new WordCountController()
    controller.dispatcher()

  }

}
