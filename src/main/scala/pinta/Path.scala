package pinta

sealed trait PathSegment
final case class LineTo (dest: Point) extends PathSegment
final case class QuadTo (dest: Point, control: Point) extends PathSegment
final case class CubicTo(dest: Point, control1: Point, control2: Point) extends PathSegment
final case class ArcTo  (dest: Point) extends PathSegment
case object Close extends PathSegment

case class Path(start: Point, segments: Seq[PathSegment]) {
  def closed =
    if (segments.lastOption contains Close) this
    else copy(segments = segments :+ Close)
  override def toString = {
    (start +: segments).map(_.toString).mkString("Path(", ",", ")")
  }
}

object Path {

  def polyline(p0: Point, ps: Point*) = {
    val lines: Seq[PathSegment] = ps.map(LineTo(_))
    Path(p0, lines)
  }

  def polygon(p0: Point, ps: Point*) =
    polyline(p0, ps:_*).closed

  def coords(p: Point) = s"${p.x} ${p.y}"

  implicit val isRender = new Render[Path] {
    def render(path: Path) = {
      val desc = path.segments.map {
        case LineTo (d)         => s"L ${coords(d)}"
        case QuadTo (d, c)      => s"Q ${coords(d)}"
        case CubicTo(d, c1, c2) => s"C ${coords(d)}"
        case ArcTo  (d)         => "L"
        case Close => "Z"
      }.mkString(s"M ${path.start} ", " ", "")
      <path d={desc}></path>
    }
  }

  implicit val isMovable = new Movable[Path] {
    def moveBy(path: Path, dp: Point) = path match {
      case Path(p0, ss) =>
        val movedSegments = ss.map {
          case LineTo (d)         => LineTo (d + dp)
          case QuadTo (d, c)      => QuadTo (d + dp, c + dp)
          case CubicTo(d, c1, c2) => CubicTo(d + dp, c1 + dp, c2 + dp)
          case ArcTo  (d)         => ArcTo  (d + dp)
          case Close              => Close
        }
        Path(p0 + dp, movedSegments)
    }
  }


}

