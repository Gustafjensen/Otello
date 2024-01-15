package otello

import java.awt.* 
import scala.compiletime.ops.int
import javax.swing.Box
import java.util.ArrayList
import scala.collection.mutable.ArrayBuffer
import scala.util.control.Breaks._

object Sheet {

    var whiteTurn = true

    val sheetLength = 8

    val sheet = Array.ofDim[BoxO](sheetLength,sheetLength)

    def startingSheet(): Unit = 
        for (i <- 0 to sheetLength - 1) do 
            for (j <- 0 to sheetLength - 1) do 
                val box = new EmptyBox(i, j)
                sheet(i)(j) = box
        
        makeWhite((sheetLength/2 - 1), (sheetLength/2 - 1))
        makeWhite((sheetLength/2), (sheetLength/2))
        makeBlack((sheetLength/2 - 1), (sheetLength/2))
        makeBlack((sheetLength/2), (sheetLength/2 - 1))
    
    private def arrayMaker(i: Int, j: Int, a: Int, b: Int): Array[BoxO] = {
        val Boxes = ArrayBuffer[BoxO]()
        var x = i + a  
        var y = j + b
        while (isInRange(x, y)) {
            Boxes += sheet(x)(y)
            x += a
            y += b
        }
        Boxes.toArray
    }

    def makeMove(i: Int, j: Int): Unit =
        flipBoxes(i ,j)
        flipRest(i, j)
        changeTurn()

    private def possibleFlips(i: Int, j: Int): Array[Array[BoxO]] = {

        val pFlips = ArrayBuffer[Array[BoxO]]()

        pFlips += arrayMaker(i, j, 0, 1)
        pFlips += arrayMaker(i, j, 1, 1)
        pFlips += arrayMaker(i, j, 1, 0)
        pFlips += arrayMaker(i, j, 1, -1)
        pFlips += arrayMaker(i, j, -1, 0)
        pFlips += arrayMaker(i, j, -1, -1)
        pFlips += arrayMaker(i, j, 0, -1)
        pFlips += arrayMaker(i, j, -1, 1)

        pFlips.toArray
    }

    def shouldFlip(i: Int, j: Int): Array[Array[BoxO]] = {
        
        val placedBox = sheet(i)(j)
        val flipMatrix = new ArrayBuffer[Array[BoxO]]
        

        def checkIfFlipped(originalArray: Array[BoxO], array: Array[BoxO], index: Int): Unit =
            var currentIndex = index
            array.headOption match {
                case Some(currentBox) =>
                    
                    if (currentBox.equals(placedBox)) then
                        flipMatrix += originalArray.take(currentIndex)
                    
                    else if (!isEmptyBox(currentBox)) then
                        currentIndex += 1
                        checkIfFlipped(originalArray, array.tail, currentIndex)
                case None => 
            }
        
        for (array <- possibleFlips(i, j)) do 
            checkIfFlipped(array, array, 0)        
            

        flipMatrix.toArray
    }

    private def flipRest(i: Int, j: Int): Unit =
        for (arrays <- shouldFlip(i, j)) do
            for (box <- arrays) do
                flipBoxes(box.x, box.y)
    

    def isPossibleMove(i: Int, j: Int): Boolean = 
        
        if (!isEmptyBox(sheet(i)(j))) then 
            false
        else 
            var isPossible = false
            
            for (arrays <- possibleFlips(i, j)) do
                var foundEmpty = false
                var k = 0

                while (k < arrays.length && !foundEmpty && !isPossible) do
                    if (getOppositeBox().equals(arrays(k))) then
                        k += 1
                    else if (isEmptyBox(arrays(k))) then 
                        foundEmpty = true
                    else if (k > 0) 
                        isPossible = true
                        foundEmpty = true
                    else 
                        foundEmpty = true
                                      
            isPossible


    def checkIfOver(): Boolean = 
        var fullBoard = true
        var allSameColor = true
        var noPossibleMove = true

        for (arrays <- sheet) do 
            for (boxes <- arrays) do
                if (isEmptyBox(boxes)) then
                    fullBoard = false
                    if isPossibleMove(boxes.x, boxes.y) then
                        noPossibleMove = false 

                if (getSameBox().equals(boxes)) then
                    allSameColor = false
                    
        fullBoard || allSameColor || noPossibleMove
    

    def countPoints(): (Int, Int) = 
        var whitePoints = 0
        var blackPoints = 0
        for (arrays <- sheet) do
            for (box <- arrays) do
                if (box.isWhite()) then
                    whitePoints += 1
                if (box.isBlack()) then 
                    blackPoints += 1
        (whitePoints, blackPoints)


    def flipBoxes(i: Int, j: Int): Unit = 
        if (whiteTurn) 
            makeWhite(i, j)
        
        else 
            makeBlack(i, j)
        
    def makeBlack(i: Int, j: Int): Unit = 
        sheet(i)(j) = new BlackBox(i, j)

    def makeWhite(i: Int, j: Int): Unit = 
        sheet(i)(j) = new WhiteBox(i, j)

    def isInRange(i: Int, j: Int): Boolean =
        i <= sheetLength - 1 && i >= 0 &&
        j <= sheetLength - 1 && j >= 0
    
    def changeTurn(): Unit = 
        whiteTurn = !whiteTurn

    def getOppositeBox(): BoxO =
        if (whiteTurn) then
            new BlackBox(sheetLength + 2, sheetLength + 2)
        else new WhiteBox(sheetLength + 2, sheetLength + 2)

    def getSameBox(): BoxO = 
        if (whiteTurn) then
            new WhiteBox(sheetLength + 2, sheetLength + 2)
        else new BlackBox(sheetLength + 2, sheetLength + 2)

    def isEmptyBox(box: BoxO): Boolean = 
        val emptyBox = new EmptyBox(sheetLength + 2, sheetLength + 2)
        box.equals(emptyBox)
    

    def getBox(i: Int, j: Int): BoxO = 
        sheet(i)(j)
}