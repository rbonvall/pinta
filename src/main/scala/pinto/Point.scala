package pintoz

import squants.space.{Length, Angle, LengthUnit, Radians}

case class Point(x: Length, y: Length) {

  /** Angle that the point spans with the positive x-axis. */
  def angle: Angle = {
    val Point(xu, yu) = this in x.unit
    Radians(math.atan2(yu.value, xu.value))
  }

  /** Distance of the point to the origin. */
  def magnitude: Length = {
    val Point(xu, yu) = this in x.unit
    x.unit(math.hypot(xu.value, yu.value))
  }

  /** Polar coordinates of the point. */
  def polar = (magnitude, angle)

  /** Distance of the point to another point. */
  def distTo(q: Point): Length = (q - this).magnitude

  type F = Double => Double
  def bimap(fx: F, fy: F) = Point(x.map(fx), y.map(fy))
  def map  (f: F)         = bimap(f, f)
  def mapX (fx: F)        = bimap(fx, identity)
  def mapY (fy: F)        = bimap(identity, fy)

  def +(q: Point) = new Point(x + q.x, y + q.y)
  def -(q: Point) = new Point(x - q.x, y - q.y)
  def *(c: Double) = new Point(x * c, y * c)
  def /(c: Double) = new Point(x / c, y / c)

  def unary_- = new Point(-x, -y)
  def unary_+ = this

  def left (dx: Length) = new Point(x - dx, y)
  def right(dx: Length) = new Point(x + dx, y)
  def down (dy: Length) = new Point(x, y - dy)
  def up   (dy: Length) = new Point(x, y + dy)

  /** Reflect the point across the x-axis. */
  def flipX = new Point(-x, y)

  /** Reflect the point across the y-axis. */
  def flipY = new Point(x, -y)

  /** An equivalent point whose coordinates are expressed in the given unit. */
  def in(unit: LengthUnit) =
    if (x.unit == unit && y.unit == unit) this
    else new Point(x in unit, y in unit)

}

object Point {

  val zero = Length.primaryUnit(0)
  val origin = new Point(zero, zero)

  /** Create a point given its polar coordinates. */
  def apply(r: Length, θ: Angle): Point =
    new Point(r * θ.cos, r * θ.sin)

  /** Create a point on the x-axis. */
  def x(value: Length) = new Point(value, value.unit(0))

  /** Create a point on the y-axis. */
  def y(value: Length) = new Point(value.unit(0), value)

  /** Create a point for which each of its coordinates comes from a different point. */
  def corner(xPoint: Point, yPoint: Point) = new Point(xPoint.x, yPoint.y)

  /** Create a point from coordinates given as a non-dimensional
   *  multiple of a grid size. */
  def apply[NX, NY](x: NX, y: NY)
                   (implicit grid: Length,
                             nx: Numeric[NX],
                             ny: Numeric[NY]): Point =
    new Point(nx.toDouble(x) * grid, ny.toDouble(y) * grid)

  /** Create a point from its polar coordinates, where the magnitude
   *  is given as a non-dimensional multiple of a grid size. */
  def apply[NR](r: NR, θ: Angle)
               (implicit grid: Length,
                         nr: Numeric[NR]): Point =
    Point(nr.toDouble(r), θ)

  /** Point (1, 1) in the given grid size. */
  def unitary(implicit grid: Length) = new Point(grid, grid)

}

