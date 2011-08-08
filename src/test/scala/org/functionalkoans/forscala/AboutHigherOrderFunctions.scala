package org.functionalkoans.forscala

import support.KoanSuite
import support.BlankValues._
import org.scalatest.matchers.ShouldMatchers
import collection.immutable.List

class AboutHigherOrderFunctions extends KoanSuite with ShouldMatchers {

  koan("A variable referencing an anonymous function") {
    val lambda = {
      x: Int => x + 1
    }
    def result = List(1, 2, 3) map lambda //FYI: map runs a function on each element of a collection
    result should be(List(2, 3, 4))
  }

  koan("Another way for a variable to reference an anonymous function") {
    val lambda = (x: Int) => x + 1
    def result = List(1, 2, 3) map lambda
    result should be(List(2, 3, 4))
  }

  koan("Declaring a variable to reference an explicitly created function") {
    val lambda = new Function1[Int, Int] {
      def apply(v1: Int) = v1 + 1
    }
    def result = List(1, 2, 3) map lambda
    result should be(List(2, 3, 4))
  }

  koan("""Another way for a variable to reference an explicitly created function.  In this case (Int) => Int
          | is the same type as Function1[Int, Int]""") {
    val lambda = new ((Int) => Int) {
      def apply(v1: Int) = v1 + 1
    }
    def result = List(1, 2, 3) map lambda
    result should be(List(2, 3, 4))
  }

  koan("A closure is any function that works with it's surroundings") {
    var incrementor = 1

    def closure = {
      x: Int => x + incrementor
    }

    val result = List(1, 2, 3) map closure
    result should be(List(2, 3, 4))

    incrementor = 2

    val result1 = List(1, 2, 3) map closure
    result1 should be(List(3, 4, 5))
  }

  koan("A function returning another function") {
    def addWithoutSyntaxSugar(x: Int) = {
      new Function1[Int, Int]() {
        def apply(y: Int): Int = x + y
      }
    }

    addWithoutSyntaxSugar(1)(2) should be(3)

    def add(x: Int) = (y: Int) => x + y
    add(2)(3) should be(5)

    def fiveAdder = add(5)
    fiveAdder(5) should be(10)
  }


  koan("function taking another function as parameter. Helps in compositioning functions") {
    def makeUpper(xs: List[String]) = xs map {
      _.toUpperCase
    }
    def makeWhatEverYouLike(xs: List[String], sideEffect: String => String) = {
      xs map sideEffect
    }
    makeUpper(List("abc", "xyz", "123")) should be(List("ABC", "XYZ", "123"))

    makeWhatEverYouLike(List("ABC", "XYZ", "123"), {
      x => x.toLowerCase
    }) should be(List("abc", "xyz", "123"))
    //using it inline
    List("Scala", "Erlang", "Clojure") map {
      _.length
    } should be(List(5, 6, 7))
  }

  koan("Currying is a technique to transform function with multiple parameters to function with one parameter") {
    def multiply(x: Int, y: Int) = x * y
    val multiplyCurried = (multiply _).curried
    multiply(4, 5) should be(20)
    multiplyCurried(3)(2) should be(6)
  }

  koan("Currying allows you to create specialized version of generalized function") {
    def customFilter(f: Int => Boolean)(xs: List[Int]) = {
      xs filter f
    }
    def onlyEven(x: Int) = x % 2 == 0
    val xs = List(12, 11, 5, 20, 3, 13, 2)
    customFilter(onlyEven)(xs) should be(List(12, 20, 2))

    val onlyEvenFilter = customFilter(onlyEven) _
    onlyEvenFilter(xs) should be(List(12, 20, 2))
  }

  koan("Using a method inside a class to create a function") {
    class PokerTable(name: String, capacity: Int, players: List[String] = Nil) {
      def isAvailable(amount: Int) = (capacity - amount < 0)

      def addPlayer(player: String) = {
        require(players.size < capacity, "Table Full")
        new PokerTable(name, capacity, players :+ player)
      }
    }

    val theTexas = new PokerTable("The Texas", 9)
    val oldWest = new PokerTable("Old West", 3, Nil)
    val whirlwind = new PokerTable("Whirlwind", 3, Nil)
    val enchantress = new PokerTable("Enchantress", 15, Nil)

    val x = theTexas.isAvailable _
    List(12, 2, 3, 4, 5, 6, 11, 44).filter(x)
  }
}
