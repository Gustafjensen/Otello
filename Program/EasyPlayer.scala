package otello

class EasyPlayer extends Opponent {
  
    def doOpposingPlayerTurn(): Unit = 

        var worstMove = (0, 0)
        var leastFlipped = 99

        for (i <- 0 until Sheet.sheetLength) do 
            for (j <- 0 until Sheet.sheetLength) do

                if Sheet.isPossibleMove(i, j) then
                    val amountOfFlips = Sheet.shouldFlip(i, j).flatten.size
                    if leastFlipped > amountOfFlips then
                        leastFlipped = amountOfFlips
                        worstMove = (i, j)
        
        //Thread.sleep(2000)
        Sheet.makeMove(worstMove._1, worstMove._2)
}
