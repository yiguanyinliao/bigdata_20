package chapter12

import scala.util.matching.Regex

object Scala02_Regex1 {

  def main(args: Array[String]): Unit = {

    // TODO Scala - 正则表达式

    // 判断字符串是不是一个电话号码（手机号码）
    // 1.全部都是数字
    // 2.长度为11位

    // TODO 1.声明规则
//    val r: Regex = "^\\d$".r
    val r: Regex = "^1[35][2567]\\d{8}$".r

    // TODO 2.准备数据
    val s = "13623352515"
    // TODO 3.匹配数据
    val maybeString: Option[String] = r.findFirstIn(s)
    if(maybeString.isEmpty) {
      println("没有符合规则的内容")
    }else{
      println(maybeString.get)
    }
  }

}
