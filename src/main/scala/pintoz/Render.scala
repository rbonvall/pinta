package pintoz

import scala.xml.Elem

object Render {

  def coords(p: Point) = s"${p.x} ${p.y}"

  def renderPath(path: Path): Elem = {
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
