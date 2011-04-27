package org.functionalkoans.forscala


import support.KoanSuite
import org.scalatest.matchers.ShouldMatchers

/**
 * Created by Daniel Hinojosa
 * User: Daniel Hinojosa
 * Date: 4/27/11
 * Time: 12:45 PM
 * url: <a href="http://www.evolutionnext.com">http://www.evolutionnext.com</a>
 * email: <a href="mailto:dhinojosa@evolutionnext.com">dhinojosa@evolutionnext.com</a>
 * tel: 505.363.5832
 */
class AboutActors extends KoanSuite with ShouldMatchers {
  koan("Basic Actor that extends Actor, this will ben invoked in separate thread") {
    import actors.Actor
    class AbrahamLincoln extends Actor {
      def act() {
        println("Four score and seven years ago.")
      }
    }

    val abe = new AbrahamLincoln
    abe.start()
  }

  koan("Basic anonymous actor") {
    import actors.Actor._
    val jfk = actor {
      println("Ask not what your country can do for you")
    }
  }

  koan("""Messages can be sent to actors. The ! calls are inspired by Erlang
        | React method returns no result""") {
    import actors.Actor._
    val guessNumber = actor {
       loop {
         react {
           case i: Int if (i > 64) => println("Too high")
           case i: Int if (i < 64) => println("Too low")
           case i: Int if (i == 64) => println("Ding!")
           case _ => println("You gave me something wrong")
         }
       }
    }

    guessNumber ! 20
    guessNumber ! 23
    guessNumber ! 90
    guessNumber ! 75
    guessNumber ! 70
    guessNumber ! 61
    guessNumber ! 64
    guessNumber ! "Boing"
    guessNumber ! 65.34
  }
}