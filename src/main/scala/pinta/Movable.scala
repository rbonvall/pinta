package pinta

import squants.Length
import simulacrum._

@typeclass trait Movable[T] {
  def moveBy(t: T, Δp: Point): T

  def up   (t: T, Δy: Length) = moveBy(t, Point.y( Δy))
  def down (t: T, Δy: Length) = moveBy(t, Point.y(-Δy))
  def left (t: T, Δx: Length) = moveBy(t, Point.x(-Δx))
  def right(t: T, Δx: Length) = moveBy(t, Point.x( Δx))
}

