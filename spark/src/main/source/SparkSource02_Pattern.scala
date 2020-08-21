import java.util
import java.util.regex.{Matcher, Pattern}

import scala.collection.mutable.ArrayBuffer
import scala.util.matching.Regex

object SparkSource02_Pattern {

  def main(args: Array[String]): Unit = {

    // TODO  正则表达式

    val r: Regex = "".r
    val maybeString: Option[String] = r.findFirstIn("zhangsan")


    // Java 正则表达式
    val eqSeparatedOpt: Pattern = Pattern.compile("(--[^=]+)=(.+)")
    val s = "--class=XXXXXXXTest"
    val m: Matcher = eqSeparatedOpt.matcher(s)

    if (m.matches) {
      val name = m.group(1)
      val value = m.group(2)
      println(name,value)
    }
  }
}
