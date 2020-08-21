package chapter13.bean

case class Task() {
  var index : Int = _
  var data:List[Int] =  _
  var logic:(Int,Int) => Int = _

  def computer(): Int ={
    data.reduce(logic)
  }

}
