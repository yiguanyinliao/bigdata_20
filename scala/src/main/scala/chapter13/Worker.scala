package chapter13

import java.io.{ObjectInputStream, ObjectOutputStream}
import java.net.{ServerSocket, Socket}
import java.util.Random

import chapter13.bean.{Message, Result, Task}


class Worker {

  var workerPort = new Random().nextInt(100) + 9000

  def startUp(masterHost:String,masterPort:Int): Unit ={

    // TODO 1.建立和Master连接，告诉Master准备好了
    val masterConnection = new Socket(masterHost,masterPort)
    val message = new Message()
    message.workerPort = workerPort
    val objOut = new ObjectOutputStream(masterConnection.getOutputStream)
    objOut.writeObject(message)
    objOut.flush()
    masterConnection.close()

    // TODO 2.建立Worker服务，等待Master发送任务

    val server = new ServerSocket(workerPort)

    // TODO 3.当接收到任务时，开始计算
    //  当启动服务器之后会阻塞链接，知道有客户端连接后才会继续执行
    //  所以将这部分的代码使用线程进行封装执行.
    new Thread(
      new Runnable {
        override def run(): Unit = {
          val masterTaskConnection = server.accept()
          val objIn = new ObjectInputStream(masterTaskConnection.getInputStream)
          val task =objIn.readObject().asInstanceOf[Task]
          masterTaskConnection.close()
          println(s"Worker[${workerPort}]接收到任务数据${task.data}")

          // TODO 计算
          val result = task.computer()
          // TODO 4.建立和Master连接，将计算结果返回
          val masterResultConnection = new Socket(masterHost,masterPort)
          val r = new Result()
          r.index = task.index
          r.port = workerPort
          r.data = result
          val objOut = new ObjectOutputStream(masterResultConnection.getOutputStream)
          objOut.writeObject(r)
          objOut.flush()
          masterResultConnection.close()

        }
      }
    ).start()
  }

}
