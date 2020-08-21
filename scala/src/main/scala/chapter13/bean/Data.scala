package chapter13.bean

case class Data() {

  var workerCount:Int = _
  var data:List[Int] =  _
  var logic:(Int,Int) => Int = _

}
