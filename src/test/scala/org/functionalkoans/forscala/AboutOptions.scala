package org.functionalkoans.forscala

import support.KoanSuite
import support.BlankValues._
import org.scalatest.matchers.ShouldMatchers

class AboutOptions extends KoanSuite with ShouldMatchers {

  koan("Option can have one of two values - Some or None") {
    val someValue: Option[String] = Some("I am wrapped in something")
    someValue.get should be("I am wrapped in something")

    val nullValue: Option[String] = None
    nullValue should be(None)
  }

  koan("Represent null with None because null is a bad idea") {
    val value1 = maybeItWillReturnSomething(true)
    val value2 = maybeItWillReturnSomething(false)

    value1.get should be("Found value")
    intercept[java.util.NoSuchElementException] {
      value2.get
    }
  }

  koan("Provide a default value for None") {
    val value1 = maybeItWillReturnSomething(true)
    val value2 = maybeItWillReturnSomething(false)

    value1 getOrElse "No value" should be("Found value")
    value2 getOrElse "No value" should be("No value")
    value2 getOrElse {
      "default function"
    } should be("default function")

  }

  koan("checking whether option has value") {
    val value1 = maybeItWillReturnSomething(true)
    val value2 = maybeItWillReturnSomething(false)

    value1.isEmpty should be(false)
    value2.isEmpty should be(true)
  }

  koan("Option can also be used with pattern matching") {
    val someValue: Option[Double] = Some(20.0)
    val value = someValue match {
      case Some(v) => v
      case None => 0.0
    }
    value should be(20.0)
    val noValue: Option[Double] = None
    val value1 = noValue match {
      case Some(v) => v
      case None => 0.0
    }
    value1 should be(0.0)

  }

  koan("Option is more than just a replacement of null, its also a collection") {
    Some(10) map {
      _ + 10
    } should be(Some(20))
    Some(10) filter {
      _ == 10
    } should be(Some(10))
    Some(Some(10)) flatMap {
      _ map {
        _ + 10
      }
    } should be(Some(20))

    var newValue1 = 0
    Some(20) foreach {
      newValue1 = _
    }
    newValue1 should be(20)

    var newValue2 = 0
    None foreach {
      newValue2 = _
    }
    newValue2 should be(0)
  }

  koan("Using Option to avoid if checks for null") {
    //the ugly version
    def makeFullName(firstName: String, lastName: String) = {
      if (firstName != null) {
        if (lastName != null) {
          firstName + " " + lastName
        } else {
          null
        }
      } else {
        null
      }
    }
    makeFullName("Nilanjan", "Raychaudhuri") should be("Nilanjan Raychaudhuri")
    makeFullName("Nilanjan", null) should be(null)
    //the pretty version
    def makeFullNamePrettyVersion(firstName: Option[String], lastName: Option[String]) = {
      firstName flatMap {
        fname =>
          lastName flatMap {
            lname =>
              Some(fname + " " + lname)
          }
      }
    }
    makeFullNamePrettyVersion(Some("Nilanjan"), Some("Raychaudhuri")) should be(Some("Nilanjan Raychaudhuri"))
    makeFullNamePrettyVersion(Some("Nilanjan"), None) should be(None)
  }

  koan("Using in for comprehension") {
    val values = List(Some(10), Some(20), None, Some(15))
    val newValues = for {
      someValue <- values
      value <- someValue
    } yield value
    newValues should be(List(10, 20, 15))
  }

  def maybeItWillReturnSomething(flag: Boolean): Option[String] = {
    if (flag) Some("Found value") else None
  }
}