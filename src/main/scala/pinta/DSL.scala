package pinta

object DSL {

  type Length = squants.Length
  type Angle  = squants.Angle

  val P = Point

  implicit class PointOps(val p: Point) extends AnyVal {

    def --(q: Point) = Path.polyline(p, q)
    def --+(dp: Point) = p -- (p + dp)

    def -|(q: Point) =
      if (p.y == q.y || p.x == q.x) Path.polyline(p, q)
      else                          Path.polyline(p, q corner p, q)
    def -|+(dp: Point) = p -| (p + dp)


    def |-(q: Point) =
      if (p.y == q.y || p.x == q.x) Path.polyline(p, q)
      else                          Path.polyline(p, p corner q, q)
    def |-+(dp: Point) = p |- (p + dp)

    def circle(r: Length) = Circle(p, r)

    def rectangle(q: Point) = Path.polygon(p, p corner q, q corner p)

    def corner(q: Point) = Point.corner(p, q)
  }

  implicit class PathOps(val path: Path) extends AnyVal {

  }

}
