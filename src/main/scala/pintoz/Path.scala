package pintoz

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

}
