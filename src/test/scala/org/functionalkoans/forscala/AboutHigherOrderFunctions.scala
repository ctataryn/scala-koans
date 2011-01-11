package org.functionalkoans.forscala

import support.KoanSuite
import support.BlankValues._
import org.scalatest.matchers.ShouldMatchers

class AboutHigherOrderFunctions extends KoanSuite with ShouldMatchers {
  koan("Meet lambda") { }
  koan("Meet closure") { }
  koan("function returning another function") { }
  koan("function taking another function as parameter") { }
  koan("Currying one function with another function") { }
  koan("Composing functions to create new function") { }
}