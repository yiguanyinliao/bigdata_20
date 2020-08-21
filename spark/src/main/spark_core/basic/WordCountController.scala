package basic

import autumn_frameworker.core.TController

class WordCountController extends TController{
  private val wordCountService = new WordCountService()

  /**
    * 调度
    */
  override def dispatcher(): Unit = {

    // 分析
    val result: Array[(String, Int)] = wordCountService.analysis()
    // 获取结果
    result.foreach(println)
  }
}
