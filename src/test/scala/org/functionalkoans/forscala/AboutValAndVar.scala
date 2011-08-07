package org.functionalkoans.forscala

import org.scalatest.matchers.ShouldMatchers
import support.KoanSuite

class AboutValAndVar extends KoanSuite with ShouldMatchers {
  koan("vars may be reassigned") {
    var a = 5
    a should be(5)

    a = 7
    a should be(7)
  }

  koan("vals may not be reassigned") {
    val a = 5
    a should be(5)

    // What happens if you uncomment these lines?
    // a = 7
    // a should be (5)
  }

  koan("vals or vars can have the same name as a keyword as long as it's surrounded by `") {
    val `class` = "MyClassName"
    `class` should be("MyClassName")
  }


}
