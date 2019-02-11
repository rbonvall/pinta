package pintoz

import org.scalatest.{FunSpec, Matchers}

class Example extends FunSpec with Matchers {

  describe("DSL") {
    it("does stuff") {

      import squants.space.LengthConversions._
      import DSL._

      implicit val gridSize = 0.5.mm

      (P(1, 1) |- P(3, 4)) shouldBe Path(
        P(1, 1),
        Seq(
          LineTo(P(1, 4)),
          LineTo(P(3, 4)),
        )
      )


    }
  }


}
