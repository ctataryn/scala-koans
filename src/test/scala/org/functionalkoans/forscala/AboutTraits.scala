package org.functionalkoans.forscala

import org.scalatest.matchers.ShouldMatchers
import support.KoanSuite

/**
 * Created by Daniel Hinojosa
 * User: Daniel Hinojosa
 * Date: 12/21/10
 * Time: 8:49 PM
 * url: <a href="http://www.evolutionnext.com">http://www.evolutionnext.com</a>
 * email: <a href="mailto:dhinojosa@evolutionnext.com">dhinojosa@evolutionnext.com</a>
 * tel: 505.363.5832
 */

class AboutTraits extends KoanSuite with ShouldMatchers {
  koan("A class uses the extends keyword to mixin a trait if it is the only relationship the class inherits") {
    case class Event(name: String, source: Any)
    trait EventListener {
      def listen(event: Event): Unit
    }
    class MyListener extends EventListener {
      def listen(event: Event) {
        event match {
          case Event("Moose Stampede", _) => println("An unfortunate moose stampede occured")
          case _ => println("Nothing of importance occured")
        }
      }
    }

    val evt = Event("Moose Stampede", this)
    val myListener = new MyListener()
    myListener.listen(evt)
  }

  koan("A class can only extend from one class or trait, any subsequent extension should use the keyword \'with\'") {

    case class Event(name: String, source: Any)

    trait EventListener {
      def listen(event: Event): Unit
    }

    class OurListener

    class MyListener extends OurListener with EventListener {
      def listen(event: Event) {
        event match {
          case Event("Woodchuck Stampede", _) => println("An unfortunate woodchuck stampede occured")
          case _ => println("Nothing of importance occured")
        }
      }
    }

    val evt = Event("Woodchuck Stampede", this)
    val myListener = new MyListener()
    myListener.listen(evt)
  }

  koan("Traits are polymorphic. Any type can be referred to by another type if related by extension") {
    case class Event(name: String, source: Any)
    trait EventListener {
      def listen(event: Event): Unit
    }
    class MyListener extends EventListener {
      def listen(event: Event) {
        event match {
          case Event("Moose Stampede", _) => println("An unfortunate moose stampede occured")
          case _ => println("Nothing of importance occured")
        }
      }
    }

    val evt = Event("Moose Stampede", this)
    val myListener = new MyListener()

    myListener.isInstanceOf[MyListener] should be(true)
    myListener.isInstanceOf[EventListener] should be(true)
  }

  koan("Traits can have concrete implementations that can be mixed into concrete classes with it's own state") {
    trait Logging {
      var logCache = List[String]()

      def log(value: String) = {
        logCache = logCache :+ value
        println(value)
      }
    }

    class Welder extends Logging {
      def weld() {
        log("welding pipe")
      }
    }

    class Baker extends Logging {
      def bake() {
        log("baking cake")
      }
    }

    val welder = new Welder
    welder.weld


    val baker = new Baker
    baker.bake

    welder.logCache.size should be(1)
    baker.logCache.size should be(1)
  }
}