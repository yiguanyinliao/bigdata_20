package basic


object Spark03_ThreadLocal {


  // TODO 演示线程数据共享 工具类
  private val threadLocal:ThreadLocal[String] = new ThreadLocal[String]()

  def main(args: Array[String]): Unit = {

    val name:String = "zhangsan"
    controller(name)

  }


  def controller(name:String): Unit ={

    // TODO 使用threadLocal将数据保存到当前线程中
    threadLocal.set(name)
    service()
  }

  // 服务层
  def service(): Unit ={
    dao()
  }

  // 数据访问层，持久层
  def dao(): Unit ={

    // TODO 使用threadLocal将线程中共享数据取出来
    val name: String = threadLocal.get()
    println(name)

    // TODO 使用threadLocal从线程中将线程里的共享数据删除
    threadLocal.remove()
  }

}
