package otello

class HardPlayer extends Opponent {
  
    def doOpposingPlayerTurn(): Unit = 

        var bestMove = (0, 0)
        var mostFlipped = 0

        for (i <- 0 until Sheet.sheetLength) do 
            for (j <- 0 until Sheet.sheetLength) do

                if Sheet.isPossibleMove(i, j) then
                    val amountOfFlips = Sheet.shouldFlip(i, j).flatten.size
                    if mostFlipped < amountOfFlips then
                        mostFlipped = amountOfFlips
                        bestMove = (i, j)
        
        Sheet.makeMove(bestMove._1, bestMove._2)
    
    def isCPU() = true
    }
