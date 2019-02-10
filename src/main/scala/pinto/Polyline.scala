package pintoz

final case class Polyline(vertices: Point*) {
  def segments = (vertices zip vertices.drop(1)).map { case (p1, p2) => Segment(p1, p2) }
  def length = segments.map(_.length).fold(Point.zero)(_ + _)

}

