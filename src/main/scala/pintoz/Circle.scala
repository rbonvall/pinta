package pintoz

import squants.Length

case class Circle(center: Point, radius: Length) {
  def diameter = radius * 2
}

object Circle {

  implicit val isRender = new Render[Circle] {
    def render(c: Circle) = c match {
      case Circle(Point(x, y), r) =>
        <circ cx={x.toString} cy={y.toString} r={r.toString}></circ>
    }

  }

  implicit val circleIsMovable = new Movable[Circle] {
    def moveBy(circle: Circle, dp: Point) = circle match {
      case Circle(c, r) => Circle(c + dp, r)
    }
  }

}

