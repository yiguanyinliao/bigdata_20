package chapter05


import java.io.{BufferedReader, InputStreamReader, ObjectInputStream}
import java.net.{ServerSocket, Socket}

object Scala29_Function_Slaver1 {

  def main(args: Array[String]): Unit = {

    // 创建服务器
    val server = new ServerSocket(9999)

    // 接收客户端的连接请求
    val client: Socket = server.accept()

    // TODO 接收客户端的请求数据
    val objectIn = new ObjectInputStream(
      client.getInputStream
    )

    val ref: AnyRef = objectIn.readObject()
    // 将对象转换为指定类型的对象
    // asInstanceOf可以转换对象的类型
    val task : Task = ref.asInstanceOf[Task]
    // 关闭连接
    client.close()
    server.close()

    // TODO 开始计算
    val result: Int = task.compute()
    println("计算任务的计算结果为 = " + result)

    // Slaver向Master发送计算结果
    val socket = new Socket("localhost", 8888)
    socket.getOutputStream.write(result)
    socket.close()


  }
}
