package org.functionalkoans.forscala

import org.scalatest.matchers.ShouldMatchers
import support.KoanSuite

class AboutTypeAliases extends KoanSuite with ShouldMatchers {
  koan("A type alias merely allows you to call a class by a different name") {
    case class Student(firstName: String, lastName: String)

    type Pupil = Student
    val harryPotter = new Pupil("Harry", "Potter")
    harryPotter.firstName should be("Harry")
  }

  koan("""Although you can't make an type alias of an singleton object,
          |  you can just assign a singleton object to a var or val to
          |  create the alias.
          |
          |  When you assign a val or var to a singleton object,
          |  the type is <object-name>.type.""") {

    object StarGaze {
      def lookOntoTheSky = "I see Scorpio to the south"
    }

    val Observatory = StarGaze
    val LoversLookout: Observatory.type = Observatory

    Observatory.lookOntoTheSky should be("I see Scorpio to the south")
    LoversLookout.lookOntoTheSky should be("I see Scorpio to the south")
  }

  koan("""You can use <object-name>.type as a method parameter to accept singleton objects.""") {
    object StarGaze {
      def lookOntoTheSky = "I see Scorpio to the south"
    }

    def lookout(starGazer:StarGaze.type) = {
       starGazer.lookOntoTheSky
    }
    lookout(StarGaze) should be ("I see Scorpio to the south")
  }
}