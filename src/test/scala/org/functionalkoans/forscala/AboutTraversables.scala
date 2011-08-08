package org.functionalkoans.forscala

import org.scalatest.matchers.ShouldMatchers
import support.KoanSuite
import collection.immutable.TreeSet

class AboutTraversables extends KoanSuite with ShouldMatchers {

  koan("""Traverables are the superclass of Lists, Arrays, Maps, Sets, Streams, and more.  
          |   The methods involved can be applied to each other in a different type.  ++ appends 
          |   two Traversables together.""") {

    val set = Set(1, 9, 10, 22)
    val list = List(3, 4, 5, 10)
    val result = set ++ list
    result.size should be(7)

    val result2 = list ++ set
    result2.size should be(8)
  }

  koan("""map will apply the given function on all elements of a
          |  Traversable and return a new collection of the result.""") {
    val set = Set(1, 3, 4, 6)
    val result = set.map(_ * 4)
    result.last should be(24)
  }

  koan("""flatten will smash all child Traversables within a Traversable""") {
    val list = List(List(1), List(2, 3, 4), List(5, 6, 7), List(8, 9, 10))
    list.flatten should be(List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))
  }

  koan("""flatMap will not only apply the given function on all elements of a Traversable,
          |  but all elements within the elements and flatten the results""") {
    val list = List(List(1), List(2, 3, 4), List(5, 6, 7), List(8, 9, 10))
    val result = list.flatMap(_.map(_ * 4))
    result should be(List(4, 8, 12, 16, 20, 24, 28, 32, 36, 40))
  }

  koan("""flatMap of Options will filter out all Nones and Keep the Somes""") {
    val list = List(1, 2, 3, 4, 5)
    val result = list.flatMap(it => if (it % 2 == 0) Some(it) else None)
    result should be(List(2, 4))
  }

  koan("""collect will apply a partial function to all elements of a Traversable
          and will return a different collection. In this koan, a case fragment is a partial function.""") {
    val list = List(4, 6, 7, 8, 9, 13, 14)
    val result = list.collect {
      case x: Int if (x % 2 == 0) => x * 3
    }
    result should be(List(12, 18, 24, 42))
  }

  koan("""collect will apply a partial function to all elements of a Traversable
          |  and will return a different collection. In this koan, two case fragments are chained to create
          |  a more robust result.""") {
    val list = List(4, 6, 7, 8, 9, 13, 14)
    val partialFunction1: PartialFunction[Int, Int] = {
      case x: Int if (x % 2 == 0) => x * 3
    }
    val partialFunction2: PartialFunction[Int, Int] = {
      case y: Int if (y % 2 != 0) => y * 4
    }
    val result = list.collect(partialFunction1 orElse partialFunction2)
    result should be(List(12, 18, 28, 24, 36, 52, 42))
  }

  koan("""foreach will apply a function to all elements of a Traversable, but unlike
          | the map function, it will not return anything since the return type is Unit, which
          | is like a void return type in Java, C++""") {
    val list = List(4, 6, 7, 8, 9, 13, 14)
    list.foreach(num => println(num * 4))
    list should be(List(4, 6, 7, 8, 9, 13, 14))
  }

  koan("""toArray will convert any Traversable to an Array, which is a special wrapper around a
          |  primitive Java array.""") {
    val set = Set(4, 6, 7, 8, 9, 13, 14)
    val result = set.toArray
    result.isInstanceOf[Array[Int]] should be(true)
  }

  koan("""toList will convert any Traversable to a List.""") {
    val set = Set(4, 6, 7, 8, 9, 13, 14)
    val result = set.toList
    result.isInstanceOf[List[Int]] should be(true)
  }

  koan("""toList, as well as other conversion methods like toSet, toArray,
          |  will not convert if the collection type is the same.""") {
    val list = List(5, 6, 7, 8, 9)
    val result = list.toList
    (result eq list) should be(true) //Reminder: eq tests for reference equality
  }

  koan("""toIterable will convert any Traversable to an Iterable. This is a base
          |  trait for all Scala collections that define an iterator method to step
          |  through one-by-one the collection's elements.
          |  (see AboutIterable koan).""") {

    val set = Set(4, 6, 7, 8, 9, 13, 14)
    val result = set.toIterable
    result.isInstanceOf[Iterable[Int]] should be(true)
  }

  koan("""toSeq will convert any Traversable to a Seq which is an ordered Iterable
          |  and is the superclass to List, Queues, and Vectors.  Sequences provide
          |  a method apply for indexing. Indices range from 0 up the the
          |  length of a sequence.""") {
    val set = Set(4, 6, 7, 8, 9, 13, 14)
    val result = set.toSeq
    result.isInstanceOf[Seq[Int]] should be(true)
  }

  koan("""toIndexedSeq will convert any Traversable to an IndexedSeq which is an indexed sequence used in
          | Vectors and Strings""") {
    val set = Set(4, 6, 7, 8, 9, 13, 14)
    val result = set.toIndexedSeq
    result.isInstanceOf[IndexedSeq[Int]] should be(true)
  }

}
