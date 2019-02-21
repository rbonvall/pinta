package pinta

import squants.Length

final case class Rectangle(center: Point, width: Length, height: Length) {
  def nw: Point = center + Point(-width / 2,  height / 2)
  def sw: Point = center + Point(-width / 2, -height / 2)
  def ne: Point = center + Point( width / 2,  height / 2)
  def se: Point = center + Point( width / 2, -height / 2)
  def corners = Seq(ne, nw, sw, se)
}

object Rectangle {

  def fromCorners(v1: Point, v2: Point): Rectangle = {
    val Point(w, h) = (v1 - v2)
    val center = (v1 + v2) / 2.0
    Rectangle(center, w.abs, h.abs)
  }

  implicit val isMovable = new Movable[Rectangle] {
    def moveBy(rectangle: Rectangle, dp: Point) = rectangle match {
      case Rectangle(c, w, h) => Rectangle(c + dp, w, h)
    }
  }

}
