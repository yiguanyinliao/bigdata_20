package req.controller

import autumn_frameworker.core.TController
import req.service.HotCategroyTop10Service

class HotCategoryTop10Controller extends TController{
  private val hotCategoryTop10Service = new HotCategroyTop10Service()

  /**
    * 调度
    */
  override def dispatcher(): Unit = {
    val result = hotCategoryTop10Service.analysis()
    result.foreach(println)
  }
}
