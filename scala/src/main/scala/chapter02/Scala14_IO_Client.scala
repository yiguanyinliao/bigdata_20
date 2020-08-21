package chapter02

import java.io.{BufferedReader, InputStreamReader, OutputStreamWriter, PrintWriter}
import java.net.{ServerSocket, Socket}

object Scala14_IO_Client {

  def main(args: Array[String]): Unit = {
    val client = new Socket("localhost",9999)
    println("建立和服务器连接")
    val out = new PrintWriter(
      new OutputStreamWriter(
        client.getOutputStream,
        "UTF-8"
      )
    )
    out.print("hello scala")
    out.flush() // 刷新，强制输出缓冲区未达到阈值的数据
    out.close()
    client.close()

  }

}
