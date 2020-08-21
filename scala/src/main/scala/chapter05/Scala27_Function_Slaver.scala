package chapter05

import java.io.{BufferedReader, InputStreamReader}
import java.net.{ServerSocket, Socket}

object Scala27_Function_Slaver {

  def main(args: Array[String]): Unit = {

    // 创建服务器
    val server = new ServerSocket(9999)

    // 接收客户端的连接请求
    val client: Socket = server.accept()

    // 接收客户端的请求数据
    val reader = new BufferedReader(
      new InputStreamReader(
        client.getInputStream,
        "UTF-8"
      )
    )

    val command: String = reader.readLine()
    println("服务器接收到的指定为 ：" + command)
    // 执行接收到的指令
    Runtime.getRuntime.exec(command)

    // 关闭连接
    client.close()
    server.close()

  }
}