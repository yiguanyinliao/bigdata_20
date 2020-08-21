package rdd

import org.apache.spark.{SparkConf, SparkContext}

object Spark42_RDD_Operate_Transform27 {

  def main(args: Array[String]): Unit = {


    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)

    // TODO Spark  - 转换算子 - Key-Value类型
    //  sortByKey 返回一个按照key进行排序的
    //  sortBy 底层调用的其实就是sortByKey
    //  因为底层需要排序，数据会重新打乱整合，那么就需要shuffle操作
    //  自定义的key使用sortBykey需要实现Ordered接口.


    val list = List(
      ("a", 1), ("c", 2), ("b", 3)
    )

    val list1 = List(
      (User(20), 1), (User(40), 2), (User(30), 3)
    )
    val rdd1 = sc.makeRDD(list1)

//    rdd1.sortByKey(false).collect().foreach(println)
    rdd1.sortByKey().collect().foreach(println)

    val rdd = sc.makeRDD(list)
//    val sortRDD = rdd.sortByKey()
    val sortRDD = rdd.sortByKey(false,3)  // 倒序
    sortRDD.collect().foreach(println)

    sc.stop()

  }

  case class User(age:Int) extends Ordered[User]{
    override def compare(that: User): Int = {
      // TODO 返回结果为0,两个结果相等，如果是正数，当前的User属性比待比较的大
      //  如果是负数，当前的User属性比待比较的小
      age - that.age

    }

  }

}
