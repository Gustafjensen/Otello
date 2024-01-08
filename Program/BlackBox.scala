package otello 

class BlackBox(i: Int, j: Int) extends BoxO(i, j) {
  
  override def isBlack() = true
  override def toString(): String = {
    "Black"
  }

}