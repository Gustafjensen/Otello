package otello

class WhiteBox(i: Int, j: Int) extends BoxO(i, j) {

  override def isWhite(): Boolean = true
  override def toString(): String = {
    "White"
  }  
}
