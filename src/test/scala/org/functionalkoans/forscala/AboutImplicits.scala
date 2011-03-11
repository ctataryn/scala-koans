package org.functionalkoans.forscala

import org.scalatest.matchers.ShouldMatchers
import support.KoanSuite

/**
 * Created by Daniel Hinojosa
 * User: Daniel Hinojosa
 * Date: 3/10/11
 * Time: 5:38 PM
 * url: <a href="http://www.evolutionnext.com">http://www.evolutionnext.com</a>
 * email: <a href="mailto:dhinojosa@evolutionnext.com">dhinojosa@evolutionnext.com</a>
 * tel: 505.363.5832
 */
class AboutImplicits extends KoanSuite with ShouldMatchers {

  koan("""Implicits wrap around existing classes to provide extra functionality
           |   This is similar to \'monkey patching\' in Ruby, and Meta-Programming in Groovy.
           |   Creating a method isOdd for Int, which doesn't exist""") {

    class KoanIntWrapper(val original: Int) {
      def isOdd() = original % 2 != 0
    }

    implicit def thisMethodNameIsIrrelevant(value: Int) = new KoanIntWrapper(value)

    19.isOdd() should be(true)
    20.isOdd() should be(false)
  }

  koan("""Implicits rules can be imported into your scope with an import""") {
    object MyPredef {
      class KoanIntWrapper(val original: Int) {
        def isOdd() = original % 2 != 0

        def isEven() = !isOdd()
      }
      implicit def thisMethodNameIsIrrelevant(value: Int) = new KoanIntWrapper(value)
    }
    import MyPredef._ //imported implicits come into effect with in this scope
    19.isOdd() should be(true)
    20.isOdd() should be(false)
  }


  //I believe these views are used in collections
}