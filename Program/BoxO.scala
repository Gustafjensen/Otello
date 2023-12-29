package otello

import scala.compiletime.ops.int

abstract class BoxO(i: Int, j: Int) {

  val x = i
  val y = j

  def getBox(): BoxO = {
    return this
  }

  override def equals(other: Any): Boolean = {
    other.toString().equals(this.toString())
  }

  def getName(): (Int, Int) = {
    (i, j)
  }

  override def toString(): String 

}
