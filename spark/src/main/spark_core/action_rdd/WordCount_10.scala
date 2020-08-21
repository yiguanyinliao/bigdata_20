package action_rdd

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

object WordCount_10 {

  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local").setAppName("Spark-WordCount-10")
    val sc: SparkContext = new SparkContext(sparkConf)
    val lines: RDD[String] = sc.textFile("input/word.txt")

    /**
      * groupBy 实现WordCount
      *
      */
    def wordCount1(lines:RDD[String]) = {
      lines.flatMap(_.split(" ")).groupBy(w => w).map{
        case (word,iter) => {
          (word,iter.size)
        }
      }.collect().foreach(println)
    }

    /**
      * groupBy 实现WordCount
      *
      */
    def wordCount9(lines:RDD[String]) = {
      lines.flatMap(_.split(" ")).groupBy(w => w).mapValues(list => list.size).collect().foreach(println)
    }

    /**
      * groupByKey 实现WordCount
      *
      */
    def wordCount2(lines:RDD[String]) ={
      lines.flatMap(_.split(" ")).map(w => (w,1)).groupByKey().map{
        case (word,iter) => {
          (word,iter.size)
        }
      }.collect().foreach(println)
    }

    /**
      * reduceByKey 实现WordCount
      *
      */
    def wordCount3(lines:RDD[String]) = {
      lines.flatMap(_.split(" ")).map(w => (w,1)).reduceByKey(_+_).collect().foreach(println)
    }

    /**
      * aggregateByKey 实现WordCount
      *
      */
    def wordCount4(lines:RDD[String])={
      lines.flatMap(_.split(" ")).map(w => (w,1)).aggregateByKey(0)(
        (x,y)=> x +y,
        (x,y)=> x +y,
      ).collect().foreach(println)
    }

    /**
      * foldByKey 实现WordCount
      *
      */
    def wordCount5(lines:RDD[String]) = {
      lines.flatMap(_.split(" ")).map(w => (w,1)).foldByKey(0)((x,y) => x+y).collect().foreach(println)
    }

    /**
      * combineByKey 实现WordCount
      *
      */
    def wordCount6(lines:RDD[String]) ={
      lines.flatMap(_.split(" ")).map(w => (w,1)).combineByKey(
        value => value, (x:Int,y) => x + y,(p1:Int,p2:Int) => p1 + p2
      ).collect().foreach(println)
    }

    /**
      * countByKey 实现WordCount
      *
      */
    def wordCount7(lines:RDD[String]) = {
      lines.flatMap(_.split(" ")).map(w => (w,1)).countByKey().foreach(println)
    }
    /**
      * countByValue 实现WordCount
      *
      */
    def wordCount8(lines:RDD[String])= {
      lines.flatMap(_.split(" ")).countByValue().foreach(println)
    }

    /**
      * cogroup 实现WordCount
      *
      */
    def wordCount10(lines:RDD[String]) ={
      val rdd1 = lines.flatMap(_.split(" "))
      val rdd2 = rdd1.map(w => (w,1))
      val rdd3 = rdd2.cogroup(rdd2)
      val rdd4 = rdd3.map {
        case (word, (iter1, iter2)) => {
          (word, iter1.sum)
        }
      }
      rdd4.collect().foreach(println)
    }
    /**
      * reduce 实现WordCount
      *
      */
    def wordCount11(lines:RDD[String])={
      lines.flatMap(_.split(" "))
        .map(word => Map[String, Int]((word, 1)))
        .reduce(
          (map1, map2) => {
            map1.foldLeft(map2)(
              (map, kv) => {
                val word = kv._1
                val count = kv._2
                map.updated(word, map.getOrElse(word, 0) + count)
              }
            )
          }
        )
    }
    /**
      * aggregate 实现WordCount
      *
      */
    def wordCount12(lines:RDD[String]) ={
      lines.flatMap(_.split(" ")).aggregate(Map[String,Int]())(
        (map,word) => {
          map.updated(word,map.getOrElse(word,0) + 1)
        },
        (map1,map2) => {
          map1.foldLeft(map2)(
            (map,kv) => {
              val word = kv._1
              val count = kv._2
              map.updated(word,map.getOrElse(word,0) + count)
            }
          )
        }
      )
    }

    /**
      * fold 实现WordCount
      *
      */
    def wordCount13(lines:RDD[String]) ={
      lines.flatMap(_.split(" ")).map(word => Map[String,Int]((word,1)))
        .fold(Map[String,Int]())(
          (map1,map2) => {
            map1.foldLeft(map2)(
              (map,kv) => {
                val word = kv._1
                val count = kv._2
                map.updated(word,map.getOrElse(word,0) + count)
              }
            )
          }
      )
    }


  }

}
