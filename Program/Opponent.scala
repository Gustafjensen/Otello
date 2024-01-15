package otello

trait Opponent {
    def doOpposingPlayerTurn(): Unit
    def isCPU(): Boolean
}
