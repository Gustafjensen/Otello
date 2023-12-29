package otello

import javax.swing._
import java.awt._
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import otello.Sheet


class GUI() extends JFrame {

    setTitle("Otello")

    val nbrBoxes = 8

    val boxSize = 50    
    val board = Array.ofDim[JButton](nbrBoxes, nbrBoxes)
    
    def initGui(): Unit = {
        setLayout(new GridLayout(nbrBoxes, nbrBoxes))
        
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
    }

    def handleButtonClick(i: Int, j: Int): Unit = {
        
        if (Sheet.isPossibleMove(i, j)) then 
            Sheet.flipBoxes(i, j)
            Sheet.flipRest(i, j)
            updateGui()
            Sheet.checkIfOver()
            Sheet.changeTurn()
        else 
            JOptionPane.showMessageDialog(null ,"Not a legal move")
    }

    def updateGui(): Unit = {
        for (i <- 0 until nbrBoxes) do 
            for ( j <- 0 until nbrBoxes) do
                val currentBox = Sheet.getBox(i, j)
                val guiButton = board(i)(j)

                guiButton.setBackground(Color.GREEN)

                currentBox match {
                    case _: BlackBox => 
                        clear(guiButton)
                        drawCircle(guiButton, Color.BLACK)
                    case _: WhiteBox => 
                        clear(guiButton)
                        drawCircle(guiButton, Color.WHITE)
                    case _: EmptyBox => 
                }
    }

    def drawCircle(button: JButton, color: Color): Unit = {
        button.setIcon(new CircleIcon(color, boxSize))
    }

    def clear(button: JButton): Unit = {
        button.setIcon(null)
    }
}