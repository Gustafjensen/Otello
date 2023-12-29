package otello

import java.awt.* 
import scala.compiletime.ops.int
import javax.swing.Box
import java.util.ArrayList
import scala.collection.mutable.ArrayBuffer
import scala.util.control.Breaks._

object Sheet {

    var whiteTurn = true
    
    val sheet = Array.ofDim[BoxO](9,9)

    for (i <- 0 to 8) do 
        for (j <- 0 to 8) do 
            val box = new EmptyBox(i, j)
            sheet(i)(j) = box
    
    makeWhite(3, 3)
    makeWhite(4, 4)
    makeBlack(3, 4)
    makeBlack(4, 3)
    
    def arrayMaker(i: Int, j: Int, a: Int, b: Int): Array[BoxO] = {
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

    def possibleFlips(i: Int, j: Int): Array[Array[BoxO]] = {

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

    def flipRest(i: Int, j: Int): Unit = {
        
        val placedBox = sheet(i)(j) 
        

        def checkflipped(originalArray: Array[BoxO], array: Array[BoxO], index: Int): Unit =
            var currentIndex = index
            array.headOption match {
                case Some(currentBox) =>
                    
                    if (currentBox.equals(placedBox)) then
                        for (k <- 0 until currentIndex) do 
                            val currentBox = originalArray(k)
                            flipBoxes(currentBox.x, currentBox.y)
                    
                    else if (!isEmptyBox(currentBox)) then
                        currentIndex += 1
                        checkflipped(originalArray, array.tail, currentIndex)
                case None => 
            }
        

        for {
            array <- possibleFlips(i, j)
        } do {
            checkflipped(array, array, 0)        
        }
    }

    def isPossibleMove(i: Int, j: Int): Boolean = 
        

        if (!isEmptyBox(sheet(i)(j))) then 
            false
        else 
            var isPossible = false
            
            for (arrays <- possibleFlips(i, j)) do
                var foundEmpty = false
                var k = 0

                while (k < arrays.length - 1 && !foundEmpty && !isPossible) do
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


    def checkIfOver(): Boolean = {
        var fullBoard = true
        var noPossibleMove = true

        for (arrays <- sheet) do 
            for (boxes <- arrays) do 
                if (isEmptyBox(boxes)) then
                    fullBoard = false
                else if (getSameBox().equals(boxes)) then
                    noPossibleMove = false
        
        fullBoard && noPossibleMove
    }


    def flipBoxes(i: Int, j: Int): Unit = {
        if (whiteTurn) {
            makeWhite(i, j)
        }
        else {
            makeBlack(i, j)
        }
    }


    def makeBlack(i: Int, j: Int): Unit = {
        sheet(i)(j) = new BlackBox(i, j)
    }

    def makeWhite(i: Int, j: Int): Unit = {
        sheet(i)(j) = new WhiteBox(i, j)
    }

    def isInRange(i: Int, j: Int): Boolean =
        i <= 7 && i >= 0 &&
        j <= 7 && j >= 0
    
    def changeTurn(): Unit = 
        whiteTurn = !whiteTurn

    def getOppositeBox(): BoxO =
        if (whiteTurn) then
            new BlackBox(10, 10)
        else new WhiteBox(10, 10)

    def getSameBox(): BoxO = 
        if (whiteTurn) then
            new WhiteBox(10, 10)
        else new BlackBox(10, 10)

    def isEmptyBox(box: BoxO): Boolean = {
        val emptyBox = new EmptyBox(10, 10)
        box.equals(emptyBox)
    }

    def getBox(i: Int, j: Int): BoxO = 
        sheet(i)(j)
    
}