package org.functionalkoans.forscala

import support.KoanSuite
import org.scalatest.matchers.ShouldMatchers

class AboutTypeBounds extends KoanSuite with ShouldMatchers {

  class Fruit

  abstract class Citrus extends Fruit

  class Orange extends Citrus

  class Tangelo extends Citrus

  class Apple extends Fruit

  class Banana extends Fruit

  koan("""You can declare an upper bound of type using the <: operator.  The <: operator designates that the
          |   operator on the left must be a subtype of the type on the right.""") {

    class MyContainer[A <: Citrus](a: A)(implicit manifest: scala.reflect.Manifest[A]) {
      private[this] var item = a

      def set(a: A) {
        item = a
      }

      def get = item

      def contents = manifest.erasure.getSimpleName
    }

    val citrusBasket: MyContainer[Citrus] = new MyContainer[Citrus](new Orange)
    citrusBasket.set(new Orange)
    citrusBasket.contents should be("Citrus")

    citrusBasket.set(new Tangelo)
    citrusBasket.contents should be("Citrus")
  }

  koan("""Variance notations are for assigment while bounds are rules on containment.
          |   Although variance and bounds are different, they can be used
          |   with one another. In this koan we can put in all anything that is a Citrus or any of
          |   subclasses and we can assign it to variables
          |   with a type of Citrus or any of its superclasses.""") {

    class MyContainer[+A <: Citrus](a: A)(implicit manifest: scala.reflect.Manifest[A]) {
      private[this] val item = a

      def get = item

      def contents = manifest.erasure.getSimpleName
    }

    val citrusBasket: MyContainer[Citrus] = new MyContainer[Citrus](new Orange) //+A allows the assignment
    citrusBasket.contents should be("Citrus")

    val citrusBasket2: MyContainer[Citrus] = new MyContainer[Citrus](new Tangelo) //+A allows the assignment
    citrusBasket2.contents should be("Citrus")

    val citrusBasket3: MyContainer[Citrus] = new MyContainer[Tangelo](new Tangelo) //+A allows the assignment
    citrusBasket3.contents should be("Tangelo")

    val citrusBasket4: MyContainer[Tangelo] = new MyContainer[Tangelo](new Tangelo) //+A allows the assignment
    citrusBasket4.contents should be("Tangelo")

    val citrusBasket5: MyContainer[Citrus] = citrusBasket4
    citrusBasket5.contents should be("Tangelo")
  }

  koan("""Variance notations are for assigment while bounds are rules on containment.  Although variance and bounds
          |   are different, they can be used
          |   with one another.  This koan will allow all citruses to be placed in the container, and will allow
          |   the container to be assigned to
          |   variables of a type less than Citrus.""") {

    class MyContainer[+A <: Citrus](a: A)(implicit manifest: scala.reflect.Manifest[A]) {
      private[this] val item = a

      def get = item

      def contents = manifest.erasure.getSimpleName
    }

    val citrusBasket = new MyContainer[Citrus](new Orange)
    citrusBasket.contents should be("Citrus")
    val citrusBasket2 = new MyContainer[Orange](new Orange)
    citrusBasket2.contents should be("Orange")
  }

  koan("""This koan uses the contravariant type upper bound by Citrus, therefore any object can be created that is a Citrus or any
         | of its subtypes.""") {

    class MyContainer[-A <: Citrus](a: A)(implicit manifest: scala.reflect.Manifest[A]) {
      private[this] var item = a

      def set(a: A) {
        item = a
      }

      def contents = manifest.erasure.getSimpleName
    }

    val citrusBasket: MyContainer[Citrus] = new MyContainer(new Orange)
    citrusBasket.contents should be("Citrus")

    val citrusBasket2: MyContainer[Citrus] = new MyContainer(new Tangelo)
    citrusBasket2.contents should be("Citrus")

    val citrusBasket3 = new MyContainer(new Tangelo)
    citrusBasket3.contents should be("Tangelo")

    val citrusBasket4: MyContainer[Tangelo] = new MyContainer(new Tangelo)
    citrusBasket4.contents should be("Tangelo")

    val citrusBasket5 = new MyContainer(new Tangelo)
    citrusBasket5.contents should be("Tangelo")
  }


  koan("""Lower bounds; invariant""") {

    class MyContainer[A >: Citrus](a: A)(implicit manifest: scala.reflect.Manifest[A]) {
      private[this] var item = a

      def set(a: A) {
        item = a
      }

      def contents = manifest.erasure.getSimpleName
    }

    val citrusBasket: MyContainer[Citrus] = new MyContainer(new Orange)
    citrusBasket.contents should be("Citrus")

    val citrusBasket2: MyContainer[Citrus] = new MyContainer(new Tangelo)
    citrusBasket2.contents should be("Citrus")

    val citrusBasket3 = new MyContainer(new Tangelo)
    citrusBasket3.contents should be("Citrus")

    val citrusBasket4 = new MyContainer(new Tangelo)
    citrusBasket4.contents should be("Citrus")
  }

  koan("""Lower bounds contravariant""") {
    class MyContainer[-A >: Citrus](a: A)(implicit manifest: scala.reflect.Manifest[A]) {
      private[this] var item = a

      def set(a: A) {
        item = a
      }

      def contents = manifest.erasure.getSimpleName
    }

    val citrusBasket: MyContainer[Citrus] = new MyContainer(new Orange)
    citrusBasket.contents should be("Citrus")

    val citrusBasket2: MyContainer[Citrus] = new MyContainer(new Tangelo)
    citrusBasket2.contents should be("Citrus")

    val citrusBasket3 = new MyContainer(new Tangelo)
    citrusBasket3.contents should be("Citrus")

    val citrusBasket4 = new MyContainer(new Tangelo)
    citrusBasket4.contents should be("Citrus")
  }

  koan("""Lower bounds covariant""") {
    class MyContainer[+A >: Citrus](a: A)(implicit manifest: scala.reflect.Manifest[A]) {
      private[this] val item = a

      def get = item

      def contents = manifest.erasure.getSimpleName
    }

    val citrusBasket: MyContainer[Citrus] = new MyContainer(new Orange)
    citrusBasket.contents should be("Citrus")

    val citrusBasket2: MyContainer[Citrus] = new MyContainer(new Tangelo)
    citrusBasket2.contents should be("Citrus")

    val citrusBasket3 = new MyContainer(new Tangelo)
    citrusBasket3.contents should be("Citrus") //Why? very important!

    val citrusBasket5 = new MyContainer(new Tangelo)
    citrusBasket5.contents should be("Citrus")
  }

  koan("""Both upper and lower bounds; invariant""") {
    class MyContainer[A >: Citrus <: AnyRef](a: A)(implicit manifest: scala.reflect.Manifest[A]) {
      private[this] var item = a

      def set(a: A) {
        item = a
      }

      def contents = manifest.erasure.getSimpleName
    }

    val citrusBasket: MyContainer[Citrus] = new MyContainer(new Orange)
    citrusBasket.contents should be("Citrus")

    val citrusBasket2: MyContainer[Citrus] = new MyContainer(new Tangelo)
    citrusBasket2.contents should be("Citrus")

    val citrusBasket3 = new MyContainer(new Tangelo)
    citrusBasket3.contents should be("Citrus")

    val citrusBasket4 = new MyContainer(new Tangelo)
    citrusBasket4.contents should be("Citrus")
  }

  koan("""Both upper and lower bounds; contravariant""") {
    class MyContainer[-A >: Citrus <: AnyRef](a: A)(implicit manifest: scala.reflect.Manifest[A]) {
      private[this] var item = a

      def set(a: A) {
        item = a
      }

      def contents = manifest.erasure.getSimpleName
    }

    val citrusBasket: MyContainer[Citrus] = new MyContainer(new Orange)
    citrusBasket.contents should be("Citrus")

    val citrusBasket2: MyContainer[Citrus] = new MyContainer(new Tangelo)
    citrusBasket2.contents should be("Citrus")

    val citrusBasket3 = new MyContainer(new Tangelo)
    citrusBasket3.contents should be("Citrus")

    val citrusBasket4 = new MyContainer(new Tangelo)
    citrusBasket4.contents should be("Citrus")
  }

  koan("""Both upper and lower bounds; covariant""") {
    class MyContainer[+A >: Citrus <: AnyRef](a: A)(implicit manifest: scala.reflect.Manifest[A]) {
      private[this] val item = a

      def get = item

      def contents = manifest.erasure.getSimpleName
    }

    val citrusBasket: MyContainer[Citrus] = new MyContainer(new Orange)
    citrusBasket.contents should be("Citrus")

    val citrusBasket2: MyContainer[Citrus] = new MyContainer(new Tangelo)
    citrusBasket2.contents should be("Citrus")

    val citrusBasket3 = new MyContainer(new Tangelo)
    citrusBasket3.contents should be("Citrus")

    val citrusBasket4 = new MyContainer(new Tangelo)
    citrusBasket4.contents should be("Citrus")

    val citrusBasket5: MyContainer[Fruit] = new MyContainer(new Tangelo)
    citrusBasket5.contents should be("Citrus")
  }
}

//TODO: Do I need koans for overriding and subclassing?
//TODO: Check if subclasses of  parents who implement traits still get traits
//TODO:  koan("() => Unit is a type, and so is => Unit, and so is Int, Int => Int")
//TODO: Do we have anything for :_* to fit it into an Array, there was some trick I am forgetting.


//(02:26:52 PM) retronym: M[A] <: M[B]  if A <: B    Covariance
//(02:27:12 PM) retronym: M[A] <: M[B]   if B <: A     Contravariance