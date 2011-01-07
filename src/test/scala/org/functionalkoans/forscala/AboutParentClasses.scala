package org.functionalkoans.forscala

import org.scalatest.matchers.ShouldMatchers
import support.KoanSuite

/**
 * Created by Daniel Hinojosa
 * User: Daniel Hinojosa
 * Date: 1/6/11
 * Time: 9:32 PM
 * url: <a href="http://www.evolutionnext.com">http://www.evolutionnext.com</a>
 * email: <a href="mailto:dhinojosa@evolutionnext.com">dhinojosa@evolutionnext.com</a>
 * tel: 505.363.5832
 */

class AboutParentClasses extends KoanSuite with ShouldMatchers {
  koan("Class heirarchy is linear, a class can only extend from one parent class") {
    class Worker(firstName: String, lastName: String) {}
    class Employee(firstName: String, lastName: String, employeeID: Long) extends Worker(firstName, lastName)
  }

  koan("A class that extends from another is polymorphic") {
    class Worker(val firstName: String, val lastName: String) {}
    class Employee(override val firstName: String, override val lastName: String,
                   val employeeID: Long) extends Worker(firstName, lastName)

    val me = new Employee("Name", "Yourself", 1233)
    val worker: Worker = me

    worker.firstName should be("Name")
    worker.lastName should be("Yourself")
  }

  koan("An abstract class, as in Java, cannot be instantiated and only inherited") {}

  koan("An class can be placed inside an abstract class just like in java") {

  }



}