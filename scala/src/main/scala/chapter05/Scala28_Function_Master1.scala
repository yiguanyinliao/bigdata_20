package chapter05
import java.io.{ObjectOutputStream, PrintWriter}
import java.net.{ServerSocket, Socket}

object Scala28_Function_Master1 {

  def main(args: Array[String]): Unit = {
    // TODO 创建客户端，建立和服务器的连接
    val client = new Socket("localhost", 9999)
    val server = new ServerSocket( 8888 )

    // TODO 向服务器发送计算任务
    val objectOut = new ObjectOutputStream(
      client.getOutputStream
    )

    val task = new Task()
    objectOut.writeObject(task)
    objectOut.flush()
    // 关系服务器的连接
    client.close()

    val socket: Socket = server.accept()
    val result: Int = socket.getInputStream.read()
    socket.close()

    println("获取计算节点的计算结果：" + result)




  }
}
