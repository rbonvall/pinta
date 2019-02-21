package pinta

import org.scalatest.{FunSpec, Matchers}
import squants.space.LengthConversions._

class RectangleSpecs extends FunSpec with Matchers {

  describe("Rectangle") {

    it("knows its four corners") {
      val r = Rectangle(Point(1.mm, -2.mm), 3.mm, 6.mm)
      r.nw shouldBe Point(-0.5.mm,  1.mm)
      r.sw shouldBe Point(-0.5.mm, -5.mm)
      r.ne shouldBe Point( 2.5.mm,  1.mm)
      r.se shouldBe Point( 2.5.mm, -5.mm)
    }

    it("can be created from two of its corners") {
      val p1 = Point( 3.mm, 4.mm)
      val p2 = Point(-2.mm, 6.mm)
      Rectangle.fromCorners(p1, p2) shouldBe Rectangle(Point(0.5.mm, 5.mm), 5.mm, 2.mm)
    }
  }


}

