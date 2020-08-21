package chapter12

import scala.util.matching.Regex

object Scala03_Regex2 {

  def main(args: Array[String]): Unit = {

    // TODO Scala - 正则表达式

    // 判断字符串是不是一个电话号码（手机号码）
    // 1.全部都是数字
    // 2.长度为11位
    // 构建正则表达式

    println(isMobileNumber("18801234567"))
    println(isMobileNumber("11111111111"))
  }
  def isMobileNumber(number: String): Boolean ={
    val regex = "^((13[0-9])|(14[5,7,9])|(15[^4])|(18[0-9])|(17[0,1,3,5,6,7,8]))[0-9]{8}$".r
    val length = number.length
    regex.findFirstMatchIn(number.slice(length-11,length)) != None

  }

}
