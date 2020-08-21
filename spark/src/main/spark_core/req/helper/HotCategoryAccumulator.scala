package req.helper

import org.apache.spark.util.AccumulatorV2
import req.bean.HotCategory

import scala.collection.mutable

/**
  * 热门品累加器
  * 1.继承AccumulateV2, 定义泛型[IN,OUT]
  *   IN:(品类ID，行为类型)
  *   OUT：mutable.Map[品类ID,HotCategory]
  * 2.重写方法(6)
  */

class HotCategoryAccumulator extends AccumulatorV2[(String,String),mutable.Map[String,HotCategory]]{

  val hotCategoryMap = mutable.Map[String,HotCategory]()  // 不加括号，是个伴生对象
//  private val map: Seq[(String, HotCategory)] => mutable.Map[String, HotCategory] = hotCategoryMap  // 不加括号，是个伴生对象
//  private val map: mutable.Map[String, HotCategory] = hotCategoryMap  //加上括号，等同于调用了apply方法，只是省略了

  // 判断累加器是否初始化
  override def isZero: Boolean = {
    hotCategoryMap.isEmpty
  }

  // 复制累加器
  override def copy(): AccumulatorV2[(String,String),mutable.Map[String,HotCategory]] = {
    new HotCategoryAccumulator
  }

  // 重置累加器
  override def reset(): Unit = {
    hotCategoryMap.clear()
  }

  // 向累加器中增加数据
  override def add(v: (String,String)) = {

    val categoryId = v._1
    val actionType = v._2

    // 查看当前累加器中是否存在相同品类的数据
    val hotCategory = hotCategoryMap.getOrElse(categoryId,HotCategory(categoryId,0,0,0))
    actionType match {
      case "click" => hotCategory.clickCount += 1
      case "order" => hotCategory.orderCount += 1
      case "pay" => hotCategory.payCount += 1
    }

    hotCategoryMap(categoryId) = hotCategory
//    hotCategoryMap.update(categoryId,hotCategory)


  }

  // 合并累加器的值
  override def merge(other: AccumulatorV2[(String,String),mutable.Map[String,HotCategory]]): Unit = {
    // map 和map的合并
    other.value.foreach{
      case(categoryId,otherHC) =>{
        val currentHC = hotCategoryMap.getOrElse(categoryId,HotCategory(categoryId,0,0,0))
        currentHC.clickCount += otherHC.clickCount
        currentHC.orderCount += otherHC.orderCount
        currentHC.payCount += otherHC.payCount

        hotCategoryMap(categoryId) = currentHC

      }
    }
  }

  // 返回累加器结果
  override def value: mutable.Map[String,HotCategory] = {
    hotCategoryMap
  }
}
