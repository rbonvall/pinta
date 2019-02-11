package pintoz

object DSL {

  type Length = squants.Length
  type Angle  = squants.Angle

  implicit class PointOps(val p: Point) extends AnyVal {

    def --(q: Point) = Path(p, LineTo(q))
    def --+(dp: Point) = p -- (p + dp)

    def -|(q: Point) =
      if (p.y == q.y || p.x == q.x) Path(p, LineTo(q))
      else                          Path(p, LineTo(Point.corner(q, p)), LineTo(q))
    def -|+(dp: Point) = p -| (p + dp)


    def |-(q: Point) =
      if (p.y == q.y || p.x == q.x) Path(p, LineTo(q))
      else                          Path(p, LineTo(Point.corner(p, q)), LineTo(q))
    def |-+(dp: Point) = p |- (p + dp)
  }

  implicit class PathOps(val path: Path) extends AnyVal {

  }

}
