package org.functionalkoans.forscala

import org.scalatest.matchers.ShouldMatchers
import support.KoanSuite

class AboutLiterals extends KoanSuite with ShouldMatchers {
  koan("Integer Literals are 32-bit and can be created from decimal, octal, or hexadecimal") {
    val a = 2
    val b = 31
    val c = 0x30F
    val d = 077
    val e = 0
    val f = -2
    val g = -31
    val h = -0x30F
    val i = -077
    a should be(2)
    b should be(31)
    c should be(783) //Hint: 30F = 783
    d should be(63) //Hint: 077 = 63
    e should be(0)
    f should be(-2)
    g should be(-31)
    h should be(-783) //Hint: 30F = 783
    i should be(-63) //Hint: 077 = 63
  }

  koan("""Long Literals are 64 bit, are specified by appending an L or l at the end;
         |   l is rarely used since it looks like a 1""") {
    val a = 2L
    val b = 31L
    val c = 0x30FL
    val d = 077L
    val e = 0L
    val f = -2l
    val g = -31L
    val h = -0x30FL
    val i = -077L

    a should be(2)
    b should be(31)
    c should be(783) //Hint: 30F = 783
    d should be(63) //Hint: 077 = 73
    e should be(0)
    f should be(-2)
    g should be(-31)
    h should be(-783) //Hint: 30F = 783
    i should be(-63) //Hint: 077 = 63
  }

  koan("""Float and Double Literals are IEEE 754 for specific,
         |   Float are 32-bit length, Doubles are 64-bit.
         |   Floats can be coerced using a f or F suffix, and
         |   Doubles can be coerced using a d or D suffix.
         |   Exponent are specified using e or E.""") {

    val a = 3.0
    val b = 3.00
    val c = 2.73
    val d = 3f
    val e = 3.22d
    val f = 93e-9
    val g = 93E-9
    val h = 0.0
    val i = 9.23E-9D

    a should be(3.0)
    b should be(3.00)
    c should be(2.73)
    d should be(3.0)
    e should be(3.22)
    f should be(93e-9)
    g should be(93E-9)
    h should be(0)
    i should be(9.23E-9D)
  }


  koan("""Trick: To get a string representation of the
          |   float or double literal add a space after the literal""") {
    3.0.toString should be("3.0")
    3.toString should be("3")
    (3. toString) should be("3.0")
    (3.0 toString) should be("3.0")
    3d.toString should be("3.0")
  }

  koan("""Boolean literals are either true or false, using the true or false keyword""") {
    val a = true
    val b = false
    a should be(true)
    b should be(false)
  }

  koan("""Character Literals can either be an a single character,
          |   an escape sequence, a Unicode octal up to 255 or a hexadecimal""") {
    val a = 'a'
    val b = 'B'
    val c = '\u0061' //unicode for a
    val d = '\141' //octal for a
    val e = '\"'
    val f = '\\'

    //format(a) is a string format, meaning the "%c".format(x)
    //will return the string representation of the char.

    "%c".format(a) should be("a")
    "%c".format(b) should be("B")
    "%c".format(c) should be("a")
    "%c".format(d) should be("a")
    "%c".format(e) should be("\"")
    "%c".format(f) should be("\\")
  }

  koan("""One-Line String Literals are surrounded by quotation marks.""") {
    val a = "To be or not to be"
    a should be("To be or not to be")
  }

  koan("""String Literals can contain escape sequences.""") {
    val a = "An \141pple \141 d\141y keeps the doctor \141w\141y"
    a should be("An apple a day keeps the doctor away")
  }

  koan("""Multiline String literals are surrounded by three quotation marks""") {
    val a = """An apple a day
    keeps the doctor away"""
    a.split('\n').size should be(2) //a.split('\n').size determines the number of lines
  }

  koan("""Multiline String literals on subsequent lines can have | to specify
          |   the start of the line, then use stripMargin to display it correctly""") {
    val a = """An apple a day
               |keeps the doctor away"""
    a.stripMargin.split('\n')(1).charAt(0) should be('k') //a.stripMargin.split('\n')(1).charAt(0)
    //gets the first character on the 2nd line
  }
}
