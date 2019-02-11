package pintoz

sealed trait PathSegment
final case class LineTo (dest: Point) extends PathSegment
final case class QuadTo (dest: Point, control: Point) extends PathSegment
final case class CubicTo(dest: Point, control1: Point, control2: Point) extends PathSegment
final case class ArcTo  (dest: Point) extends PathSegment
case object Close extends PathSegment

case class Path(start: Point, segments: PathSegment*)
