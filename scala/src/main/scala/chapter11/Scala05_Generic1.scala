package chapter11

import chapter11.bean.{Child, User,Parent}

object Scala05_Generic1 {

  def main(args: Array[String]): Unit = {

    // TODO Scala - 泛型

    // TODO 为了和Java兼容，Scala也提供了泛型的上限和下限,采用的是颜文字

    //  泛型的下限： _ >: T
    val p = new Producer[User]
    p.produce(new Message[User]())
//    p.produce(new Message[Child]())   // (X)
    p.produce(new Message[Parent]())


    //  泛型的上限：_ <: T。
    val c = new Consumer[User]
    val message:Message[_<:User] = c.consumer
    val data:User = message.data

  }

  class Producer[T] {
    def produce(message: Message[_ >: T]): Unit = {
    }
  }


  class Consumer[T] {
    def consumer: Message[_ <: T] = null
  }

  class Message[T] {
    var data: T = _
  }

}
