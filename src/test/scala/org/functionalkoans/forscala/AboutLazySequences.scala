package org.functionalkoans.forscala

import support.KoanSuite
import support.BlankValues._
import org.scalatest.matchers.ShouldMatchers

class AboutLazySequences extends KoanSuite with ShouldMatchers {

  koan("Creating a lazy collection form a strict collection") {
    val strictList = List(10, 20, 30)
    val lazyList = strictList.view
    lazyList.head should be(strictList.head)
  }

  koan("Strict collection always processes it elements but lazy collection does on demand") {
    var x = 0
    def inc = {
      x += 1;
      x
    }
    var strictList = List(inc _, inc _, inc _)
    strictList.map(f => f()).head should be(1)
    x should be(3)
    strictList.map(f => f()).head
    x should be(6)

    x = 0
    val lazyList = strictList.view
    lazyList.map(f => f()).head should be(1)
    x should be(1)
    lazyList.map(f => f()).head should be(2)
    x should be(2)
  }

  koan("Lazy collection sometimes avoid processing errors") {
    val lazyList = List(2, -2, 0, 4).view map {
      2 / _
    }
    lazyList.head should be(1)
    lazyList(1) should be(-1)
    intercept[ArithmeticException] {
      lazyList(2)
    }
  }

  koan("Lazy collections could also be infinite") {
    val infinite = Stream.from(1)
    infinite.take(4).sum should be(10)
    Stream.continually(1).take(4).sum should be(4)
  }

  koan("Always remember tail of a lazy collection is never computed unless required") {
    def makeLazy(value: Int): Stream[Int] = {
      Stream.cons(value, makeLazy(value + 1))
    }
    val stream = makeLazy(1)
    stream.head should be(1)
    stream.tail.head should be(2)
  }

}
