package chapter02

import java.io.{BufferedReader, InputStreamReader}
import java.net.{ServerSocket, Socket}

object Scala13_IO_Server {
  def main(args: Array[String]): Unit = {
    val server: ServerSocket = new ServerSocket(9999)
    while (true) {
      val socket: Socket = server.accept()
      val reader = new BufferedReader(
        new InputStreamReader(
          socket.getInputStream,
          "UTF-8"
        )
      )
      var s: String = ""
      var flg = true
      while (flg) {
        s = reader.readLine()
        if (s != null) {
          println(s)
        }else{
         flg = false
        }
      }

    }
  }
}
