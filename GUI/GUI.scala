package otello

import javax.swing._
import java.awt._
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import otello.Sheet
import java.awt.event.WindowEvent
import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global


class GUI() extends JFrame {

    setTitle("Otello")

    val nbrBoxes = Sheet.sheetLength

    val boxSize = (nbrBoxes * 6.25).toInt

    val board = Array.ofDim[JButton](nbrBoxes, nbrBoxes)

    val opponent = chooseOpponentMessage()
    
    def initGui(): Unit = 
        setLayout(new GridLayout(nbrBoxes, nbrBoxes))
        Sheet.startingSheet()
        
        for (i <- 0 until nbrBoxes) do 
            for ( j <- 0 until nbrBoxes) do
                 
                board(i)(j) = new JButton
                board(i)(j).setPreferredSize(new Dimension(boxSize, boxSize))
                
                
                board(i)(j).addActionListener(new ActionListener {
                    def actionPerformed(e: ActionEvent): Unit = 
                        handleButtonClick(i, j)
                })
                
                add(board(i)(j))

        setSize(new Dimension(500, 500))
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
        setLocationRelativeTo(null)
        setResizable(false)
        setVisible(true)

        updateGui()
    

    def handleButtonClick(i: Int, j: Int): Unit = 
        
        if (Sheet.isPossibleMove(i, j)) then 
            Sheet.makeMove(i, j)
            updateGui()
            Thread.sleep(2000)
            opponent.doOpposingPlayerTurn()
            updateGui()
            
            
        else 
            JOptionPane.showMessageDialog(null ,"Not a legal move")
        
        if (Sheet.checkIfOver()) then
            gameOverMessage("Good Game!")
    

    def updateGui(): Unit = 
        for (i <- 0 until nbrBoxes) do 
            for ( j <- 0 until nbrBoxes) do
                val currentBox = Sheet.getBox(i, j)
                val guiButton = board(i)(j)

                currentBox match 
                    case _: BlackBox => 
                        clear(guiButton)
                        drawCircle(guiButton, Color.BLACK)
                    case _: WhiteBox => 
                        clear(guiButton)
                        drawCircle(guiButton, new Color(225, 0, 0))
                    case _: EmptyBox => 
                        clear(guiButton)
        
                
    

    def drawCircle(button: JButton, color: Color): Unit = 
        button.setIcon(new CircleIcon(color, boxSize))
    

    def clear(button: JButton): Unit = 
        button.setIcon(null)
    
    
    private def chooseOpponentMessage(): Opponent = 
        val options: Array[Object] = Array("One Player", "Two Player")

        val choice = JOptionPane.showOptionDialog(
            null,
            "Welcome to Otello!",
            "Select number of players",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options(0)
        )

        choice match 
            case 0 =>
                chooseDifficultyMessage()
            case 1 =>
                new TwoPlayer

    private def chooseDifficultyMessage(): Opponent = 
        val options: Array[Object] = Array("Easy Mode", "Hard Mode")

        val choice = JOptionPane.showOptionDialog(
            null,
            "Welcome to Otello!",
            "Choose Difficulty",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options(0)
        )

        choice match 
            case 0 => 
                new EasyPlayer
            case 1 => 
                new HardPlayer
            case _ => chooseDifficultyMessage()




    private def gameOverMessage(s: String): Unit = 
        val options: Array[Object] = Array("Play again", "Exit")

        val choice = JOptionPane.showOptionDialog(
            null,
            s"Game is over! Score: White ${Sheet.countPoints()._1} , Black ${Sheet.countPoints()._2}",
            s,
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options(0)
        )

        choice match 
            case JOptionPane.YES_OPTION => 
                Sheet.startingSheet()
                updateGui()
            
            case JOptionPane.NO_OPTION => 
                dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING))
            
            case JOptionPane.CLOSED_OPTION =>
                gameOverMessage("You need to make a choice!")
        
}