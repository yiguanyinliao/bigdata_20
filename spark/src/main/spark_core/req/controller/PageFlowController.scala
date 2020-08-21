package req.controller

import autumn_frameworker.core.TController
import req.service.PageFlowService

class PageFlowController extends TController {

  private val pageFlowService = new PageFlowService()

  /**
    * 调度
    */
  override def dispatcher(): Unit = {
    val result = pageFlowService.analysis()
    result
  }
}
