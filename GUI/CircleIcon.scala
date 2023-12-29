package otello

import javax.swing.Icon
import java.awt.Component
import java.awt.Graphics
import java.awt.Color

class CircleIcon(color: Color, d: Int) extends Icon {

  override def getIconWidth(): Int = d

  override def getIconHeight(): Int = d

  override def paintIcon(c: Component, g: Graphics, x: Int, y: Int): Unit = {
    g.setColor(color)
    g.fillOval(x, y, getIconWidth(), getIconHeight())
  }
}
