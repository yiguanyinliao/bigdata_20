package req.bean

/**
  * 热门品类
  * @param categoryid
  * @param clickCount
  * @param orderCount
  * @param payCount
  */
case class HotCategory(   // 样例类中构造参数不能改，想要改，需要var声明
  categoryid:String,
  var clickCount:Int,
  var orderCount:Int,
  var payCount:Int

)