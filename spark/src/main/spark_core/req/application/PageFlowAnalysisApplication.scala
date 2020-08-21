package req.application

import autumn_frameworker.core.TApplication
import req.controller.PageFlowController

/**
  * 热门品类排行TOP10
  */
object PageFlowAnalysisApplication extends App with TApplication{


  execute{   // 执行
    val controller = new PageFlowController()
    controller.dispatcher()
  }

}
