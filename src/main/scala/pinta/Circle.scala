package pinta

import squants.Length

case class Circle(center: Point, radius: Length) {
  def diameter = radius * 2
}

object Circle {

  def fromDiameter(p1: Point, p2: Point): Circle = {
    val center = (p1 + p2) / 2.0
    val radius = (p2 - p1).magnitude / 2.0
    Circle(center, radius)
  }

  implicit val isMovable = new Movable[Circle] {
    def moveBy(circle: Circle, dp: Point) = circle match {
      case Circle(c, r) => Circle(c + dp, r)
    }
  }

}

