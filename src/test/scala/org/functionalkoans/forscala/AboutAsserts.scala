package org.functionalkoans.forscala
import org.scalatest.matchers.ShouldMatchers
import support.BlankValues.__
import support.KoanSuite

class AboutAsserts extends KoanSuite with ShouldMatchers {

    koan ("should be true") {
        assert(true)   // should be true
    }

    koan ("should be true with message") {
        assert(true, "This should be true")
    }

    koan ("should be true with should matchers") {
        true should be (true)  // should be true
    }

    koan ("should match two equal values") {
        val v1 = 4
        val v2 = 4

        assert(v1 === v2)
    }

    koan ("sometimes we expect you to fill in the values") {
        assert( 2  == 1+1 )
    }
}
