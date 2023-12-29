package otello

trait Player {
    val turn: Boolean
    def isTurn: Boolean
    def madeMove: Unit
    def getColour: String
  
}
