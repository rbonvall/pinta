package pintoz

import scala.xml.Elem
import simulacrum._

@typeclass trait Render[T] {
  def render(t: T): Elem
}

