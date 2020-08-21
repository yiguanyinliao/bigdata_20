package serial

import org.apache.spark.{SparkConf, SparkContext}

object Spark01_Ser {

  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)

    // TODO Spark - 序列化

    val list = List(1,2,3,4)
    val rdd = sc.makeRDD(list)


    // TODO 下面的代码是在Driver端执行的
    val i =20

    rdd.foreach(
      num => {
        // TODO 下面的代码是在Executer端执行的
        //  那么需要将变量i包含到当前匿名函数的内容，改变变量的生命周期
        //  所以存在闭包
        //  这里之所以会使用闭包，Driver会将变量i传递给Executor
        //  这个变量i就是通过闭包检测出来的
        //  所以Spark框架在执行作业前，必须进行闭包检测功能。

        println(num + i)
      }
    )
    sc.stop()
  }

}
