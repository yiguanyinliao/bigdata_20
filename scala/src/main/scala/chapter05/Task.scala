package chapter05


// 在网络中传输，类应该序列化
class Task extends Serializable{

  var data:Int =10
  def logic:Int => Int = (x:Int)=>{x*2}

  /**
    * 计算
    * @return
    */
  def compute():Int ={
    logic(data)
  }

}
