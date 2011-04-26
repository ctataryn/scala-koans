package org.functionalkoans.forscala

import support.KoanSuite
import org.scalatest.matchers.ShouldMatchers

/**
 * Created by Daniel Hinojosa
 * User: Daniel Hinojosa
 * Date: 4/25/11
 * Time: 9:32 PM
 * url: <a href="http://www.evolutionnext.com">http://www.evolutionnext.com</a>
 * email: <a href="mailto:dhinojosa@evolutionnext.com">dhinojosa@evolutionnext.com</a>
 * tel: 505.363.5832
 */
class AboutInfixAndPostfixOperators extends KoanSuite with ShouldMatchers  {

   koan("""Simple: Infix Operators are available if an object
           |  has a method that takes one parameter.""") {

     val g:Int = 3
     (g + 4) should be(7) // + is an infix operator
     (g.+(4)) should be (7) // same result but not using the infix operator
   }

   koan("""Infix Operators do NOT work if an object
           |  has a method that takes two parameters.""") {
     val g:String = "Check out the big brains on Brad!"
     g indexOf 'o' should be (6) //indexOf(Char) can be used as an infix operator
     //g indexOf 'o', 4 should be (6) //indexOf(Char, Int) cannot be used an infix operator
     g.indexOf('o', 7) should be (25) //indexOf(Char, Int) must use standard java/scala calls
   }

   koan("""Postfix operators work if an object
           |  has a method that takes no parameters.""") {
     val g:Int = 31
     (g toHexString) should be ("1f") //toHexString takes no params therefore can be called
                                      //as a postfix operator. Hint: The answer is 1f
   }
}