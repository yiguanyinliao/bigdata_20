package req.controller

import autumn_frameworker.core.TController
import req.bean.HotCategory
import req.service.{HotCategroyTop10Service, HotCategroyTop10SessionService}

class HotCategoryTop10SessionController extends TController{
  private val hotCategroyTop10Service = new HotCategroyTop10Service()
  private val hotCategroyTop10SessionService = new HotCategroyTop10SessionService()

  /**
    * 调度
    */
  override def dispatcher() = {

    // TOP10 热门品类
    val categories: List[HotCategory] = hotCategroyTop10Service.analysis()

    val result = hotCategroyTop10SessionService.analysis(categories)

    result.foreach(println)
  }
}
