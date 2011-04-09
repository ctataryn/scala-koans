package org.functionalkoans.forscala

import support.KoanSuite
import org.scalatest.matchers.ShouldMatchers

class AboutEnumerations extends KoanSuite with ShouldMatchers {


  koan("To create an enumeration, create an object that extends the abstract class Enumeration, " +
          "and set a val variable to the method Value.  This is a trick to give values to each val.") {

    object Planets extends Enumeration {
      //Value is a def that assigns 0 to Mercury
      val Mercury = Value

      //Value is a def that assigns 1 to Venus
      val Venus = Value

      //Value is a def that assigns 2 to Earth
      val Earth = Value

      //Value is a def that assigns 3 to Mars
      val Mars = Value

      //Value is a def that assigns 4 to Jupiter
      val Jupiter = Value

      //Value is a def that assigns 5 to Saturn
      val Saturn = Value

      //Value is a def that assigns 6 to Uranus
      val Uranus = Value

      //Value is a def that assigns 7 to Neptune
      val Neptune = Value

      //Value is a def that assigns 8 to Pluto
      val Pluto = Value
    }

    Planets.Mercury.id should be(0)
    Planets.Venus.id should be(1)

    Planets.Mercury.toString should be("Mercury") //How does it get the name? by Reflection.
    Planets.Venus.toString should be("Venus")

    (Planets.Earth == Planets.Earth) should be(true)
    (Planets.Neptune == Planets.Jupiter) should be(false)
  }

  koan("""You can create an enumeration with your own index and your own Strings, in this koan,
          | we will start with an index of one and use Greek names instead of Roman""") {
    object GreekPlanets extends Enumeration {

      //Value is a def that assigns an idx of 1 and name of Hermes to Mercury
      val Mercury = Value(1, "Hermes")

      //Value is a def that assigns an idx of 2 and name of Aphrodite to Venus
      val Venus = Value(2, "Aphrodite")

      //Value is a def that assigns and idx of 3 and name Gaia to Earth
      //FYI: Tellus is Roman for (Mother) Earth
      val Earth = Value(3, "Gaia")

      //Value is a def that assigns and idx of 4 and name Ares to Mars
      val Mars = Value(4, "Ares")

      //Value is a def that assigns and idx of 5 and name Zeus to Jupiter
      val Jupiter = Value(5, "Zeus")

      //Value is a def that assigns and idx of 6 and name Cronus to Saturn
      val Saturn = Value(6, "Cronus")

      //Value is a def that assigns and idx of 7 and name Ouranus to Uranus
      val Uranus = Value(7, "Ouranus")

      //Value is a def that assigns and idx of 8 and name Poseidon to Neptune
      val Neptune = Value(8, "Poseidon")

      //Value is a def that assigns and idx of 9 and name Hades to Pluto
      val Pluto = Value(9, "Hades")
    }

    GreekPlanets.Mercury.id should be(1)
    GreekPlanets.Venus.id should be(2)

    GreekPlanets.Mercury.toString should be("Hermes")
    GreekPlanets.Venus.toString should be("Aphrodite")

    (GreekPlanets.Earth == GreekPlanets.Earth) should be(true)
    (GreekPlanets.Neptune == GreekPlanets.Jupiter) should be(false)
  }

  koan("""Enumerations can be declared in one line if you are merely setting variables to Value""") {
    object Planets extends Enumeration {
      val Mercury, Venus, Earth, Mars, Jupiter, Saturn, Uranus, Neptune, Pluto = Value
    }

    Planets.Mercury.id should be(0)
    Planets.Venus.id should be(1)

    Planets.Mercury.toString should be("Mercury") //How does it get the name? by Reflection.
    Planets.Venus.toString should be("Venus")

    (Planets.Earth == Planets.Earth) should be(true)
    (Planets.Neptune == Planets.Jupiter) should be(false)
  }


  koan("""Enumerations can be declared with a string value only""") {
    object GreekPlanets extends Enumeration {

      //Value is a def that assigns a name of Hermes to Mercury
      val Mercury = Value("Hermes")

      //Value is a def that assigns a name of Aphrodite to Venus
      val Venus = Value("Aphrodite")

      //Value is a def that assigns a name of Gaia to Earth
      //FYI: Tellus is Roman for (Mother) Earth
      val Earth = Value("Gaia")

      //Value is a def that assigns a name of Ares to Mars
      val Mars = Value("Ares")

      //Value is a def that assigns a name of Zeus to Jupiter
      val Jupiter = Value("Zeus")

      //Value is a def that assigns a name of Cronus to Saturn
      val Saturn = Value("Cronus")

      //Value is a def that assigns a name of Ouranus to Uranus
      val Uranus = Value("Ouranus")

      //Value is a def that assigns a name of Poseidon to Neptune
      val Neptune = Value("Poseidon")

      //Value is a def that assigns a name of Hades to Pluto
      val Pluto = Value("Hades")
    }

    GreekPlanets.Mercury.id should be(0)
    GreekPlanets.Venus.id should be(1)

    GreekPlanets.Mercury.toString should be("Hermes")
    GreekPlanets.Venus.toString should be("Aphrodite")

    (GreekPlanets.Earth == GreekPlanets.Earth) should be(true)
    (GreekPlanets.Neptune == GreekPlanets.Jupiter) should be(false)
  }

  koan("You can extend the Enumeration by extending the Val class.") {

    object Planets extends Enumeration {

      val G = 6.67300E-11;

      class PlanetValue(val i: Int, val name: String, val mass: Double, val radius: Double)
              extends Val(i: Int, name: String) {

        def surfaceGravity = G * mass / (radius * radius)

        def surfaceWeight(otherMass: Double) = otherMass * surfaceGravity
      }

      //Value is a def that assigns 0 to Mercury
      val Mercury = new PlanetValue(0, "Mercury", 3.303e+23, 2.4397e6)

      //Value is a def that assigns 1 to Venus
      val Venus = new PlanetValue(1, "Venus", 4.869e+24, 6.0518e6)

      //Value is a def that assigns 2 to Earth
      val Earth = new PlanetValue(2, "Earth", 5.976e+24, 6.37814e6)

      //Value is a def that assigns 3 to Mars
      val Mars = new PlanetValue(3, "Mars", 6.421e+23, 3.3972e6)

      //Value is a def that assigns 4 to Jupiter
      val Jupiter = new PlanetValue(4, "Jupiter", 1.9e+27, 7.1492e7)

      //Value is a def that assigns 5 to Saturn
      val Saturn = new PlanetValue(5, "Saturn", 5.688e+26, 6.0268e7)

      //Value is a def that assigns 6 to Uranus
      val Uranus = new PlanetValue(6, "Uranus", 8.686e+25, 2.5559e7)

      //Value is a def that assigns 7 to Neptune
      val Neptune = new PlanetValue(7, "Neptune", 1.024e+26, 2.4746e7)

      //Value is a def that assigns 8 to Pluto
      val Pluto = new PlanetValue(8, "Pluto", 1.27e+22, 1.137e6)

    }

    Planets.Earth.mass should be(5.976e+24)
    Planets.Earth.radius should be(6.37814e6)
  }
}
