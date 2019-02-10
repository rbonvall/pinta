package pintoz

import squants.Length

case class Circle(center: Point, radius: Length) {
  def diameter = radius * 2
}

