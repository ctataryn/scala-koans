package org.functionalkoans.forscala

import org.scalatest.matchers.ShouldMatchers
import support.KoanSuite

package harkonnen {

    class AlphaBase

    class AlphaExtendBase extends AlphaBase

    protected class BetaBase {
      protected val spice = "Oregano"
    }

    private class GammaBase(private val spice:String) {
      def areYouAsSpicyAsMe_?(gammaBase: GammaBase) = {
        spice == gammaBase.spice
      }
    }

    class DeltaBase (private[this] val spice:String = "Melange") {
      def areYouAsSpicyAsMe_?(deltaBase: DeltaBase) = {
        //spice == deltaBase.spice //not accessible
      }
    }


    class AboutAccessModifiersHarkonnen extends KoanSuite with ShouldMatchers {
      koan("All classes are public by default") {
        val a = new harkonnen.AlphaBase();
      }

      koan("A protected class can be read in the same package") {
         val b = new harkonnen.BetaBase();
      }

      koan("Protected can only be accessed within a class or by a subclass") {
        class HarkonnenSoldier extends harkonnen.BetaBase {
           spice should be ("Oregano")
        }

        val harkonnenSolider = new HarkonnenSoldier
      }

      koan("A private[this] access modifier guarantees that the member is only accessible within the object") {
        val deltaBase = new DeltaBase("Scary Spice")
        val deltaBase2 = new DeltaBase("Scary Spice")
        deltaBase.areYouAsSpicyAsMe_?(deltaBase2)

      }

      koan("A private access modifier can be inspected by another object of the same type") {
         val gammaBase = new GammaBase("Sporty Spice")
         val gammaBase2 = new GammaBase("Sporty Spice")
         gammaBase.areYouAsSpicyAsMe_?(gammaBase2) should be (true)
      }
    }
}


package ordos {
    class AlphaBase

    class AlphaExtendBase extends AlphaBase

    class BetaBase

    private class GammaBase
}

package atreides {
    class AboutAccessModifiersAtreides extends KoanSuite with ShouldMatchers {
      koan("All classes are public by default") {
        val a = new harkonnen.AlphaBase();
        //val b = new harkonnen.BetaBase(); Cannot access
      }

      koan("Protected can only be accessed within a class or by a subclass") {
        // class HarkonnenSpy extends harkonnen.BetaBase; Cannot Access
      }
    }
}


class AboutAccessModifiers extends KoanSuite with ShouldMatchers  {

}