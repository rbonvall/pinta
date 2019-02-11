package pintoz

object Instances {
  import Movable.ops._

  implicit val pointIsMovable = new Movable[Point] {
    def moveBy(p: Point, dp: Point) = p + dp
  }

  implicit val pathIsMovable = new Movable[Path] {
    def moveBy(path: Path, dp: Point) = path match {
      case Path(p0, ss) =>
        val movedSegments = ss.map {
          case LineTo (d)         => LineTo (d + dp)
          case QuadTo (d, c)      => QuadTo (d + dp, c + dp)
          case CubicTo(d, c1, c2) => CubicTo(d + dp, c1 + dp, c2 + dp)
          case ArcTo  (d)         => ArcTo  (d + dp)
          case Close              => Close
        }
        Path(p0 moveBy dp, movedSegments)
    }
  }

  implicit val circleIsMovable = new Movable[Circle] {
    def moveBy(circle: Circle, dp: Point) = circle match {
      case Circle(c, r) => Circle(c + dp, r)
    }
  }

}
