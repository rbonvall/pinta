package pintoz

object DSL {

  implicit class PointOps(val p: Point) extends AnyVal {
    def --(q: Point) = Path(p, LineTo(q))
    def -|(q: Point) =
      if (p.y == q.y || p.x == q.x) Path(p, LineTo(q))
      else                          Path(p, LineTo(Point.corner(q, p)), LineTo(q))
    def |-(q: Point) =
      if (p.y == q.y || p.x == q.x) Path(p, LineTo(q))
      else                          Path(p, LineTo(Point.corner(p, q)), LineTo(q))
  }

  implicit class PolylineOps(val pl: Polyline) extends AnyVal {

  }

}
