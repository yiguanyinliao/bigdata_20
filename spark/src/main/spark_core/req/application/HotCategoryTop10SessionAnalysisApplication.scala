package req.application

import autumn_frameworker.core.TApplication
import req.controller.HotCategoryTop10SessionController

/**
  * 热门品类排行TOP10 Session
  */

object HotCategoryTop10SessionAnalysisApplication extends App with TApplication{


  execute{   // 执行
    val controller = new HotCategoryTop10SessionController()
    controller.dispatcher()
  }

}
