package chapter05

object Scala16_Function {

  def main(args: Array[String]): Unit = {

    // TODO Scala 函数柯里化
    //  将复杂的函数的参数简单化

    def test(a: Int, b: Int, c: Int): Unit = {
      for (i <- 1 to a) {

      }

      for (i <- 1 to b) {

      }

      for (i <- 1 to c) {

      }
    }

    //    val a = 1 to 5
    //    val b = 5 to 100
    //    val 100 = 1 to 1000

    test(1, 2, 3)

    def test1(a: Int)= {
      for (i <- 1 to a) {
        println(i)
      }

      def test2(b: Int)= {
        for (j <- 1 to b) {
          println(j)
        }

        def test3(c: Int) = {
          for (k <- 1 to c) {
            println(k)
          }


        }

        test3 _

      }
      test2 _


    }
    test1(5)(10)(15)


  }
}
