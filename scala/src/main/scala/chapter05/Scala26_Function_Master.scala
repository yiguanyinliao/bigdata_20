package chapter05

import java.io.PrintWriter
import java.net.Socket

object Scala26_Function_Master {

  def main(args: Array[String]): Unit = {
    // 创建客户端，建立和服务器的连接
    val client = new Socket("localhost", 9999)

    // 向服务器发送指定（指定字符串）：弹出记事本
    val writer = new PrintWriter(
      client.getOutputStream
    )

    writer.println("CMD /c notepad")
    writer.flush()
    println("向服务器发送的指定为：CMD /c notepad")

    // 关系服务器的连接
    client.close()




  }
}