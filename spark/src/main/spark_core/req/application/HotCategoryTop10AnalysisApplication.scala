package req.application

import autumn_frameworker.core.TApplication
import req.controller.HotCategoryTop10Controller

/**
  * 热门品类排行TOP10
  */
object HotCategoryTop10AnalysisApplication extends App with TApplication{


  execute{   // 执行
    val controller = new HotCategoryTop10Controller()
    controller.dispatcher()
  }

}
