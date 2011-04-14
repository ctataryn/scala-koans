package org.functionalkoans.forscala

import org.scalatest.matchers.ShouldMatchers
import support.KoanSuite

class AboutTypeSignatures extends KoanSuite with ShouldMatchers {
  koan("In Java you declare a generic type within a <>, in Scala it is []") {
    val z: List[String] = "Do" :: "Re" :: "Mi" :: "Fa" :: "So" :: "La" :: "Te" :: "Do" :: Nil
  }

  koan("Most of the time, Scala will infer the type and [] are optional") {
    val z = "Do" :: "Re" :: "Mi" :: "Fa" :: "So" :: "La" :: "Te" :: "Do" ::
            Nil //Infers that the list assigned to variable is of type List[String]
  }

  koan("A trait can be declared containing a type, where a concrete implmenter will satisfy the type") {
    trait Randomizer[A] {
      def draw: A
    }

    class IntRandomizer extends Randomizer[Int] {
      def draw = {
        import util.Random
        Random.nextInt()
      }
    }

    val intRand = new IntRandomizer
    intRand.draw should be < Int.MaxValue
  }

  koan("Class meta-information can be retrieved by class name by using classOf[className]") {
    classOf[String].getCanonicalName should be("java.lang.String")
    classOf[String].getSimpleName should be("String")
  }

  koan("Class meta-information can be derived from an object reference using getClass()") {
    val zoom = "zoom"
    zoom.getClass should be(classOf[String])
    zoom.getClass.getCanonicalName should be("java.lang.String")
    zoom.getClass.getSimpleName should be("String")
  }

  koan("isInstanceOf[className] is used to determine the if an object reference is an instance of given class") {
    trait Randomizer[A] {
      def draw: A
    }

    class IntRandomizer extends Randomizer[Int] {
      override def draw = {
        import util.Random
        Random.nextInt()
      }
    }

    val intRand = new IntRandomizer
    intRand.draw.isInstanceOf[Int] should be(true)
  }

  koan("asInstanceOf[className] is used to cast one reference to another") {
    trait Randomizer[A] {
      def draw: A
    }

    class IntRandomizer extends Randomizer[Int] {
      override def draw = {
        import util.Random
        Random.nextInt()
      }
    }

    val intRand = new IntRandomizer
    val rand = intRand
    val intRand2 = rand.asInstanceOf[IntRandomizer]
  }

  koan("""asInstanceOf[className] will throw a ClassCastException if a class derived from
         |   and the class target aren't from the same inheritance branch""") {
    trait Randomizer[A] {
      def draw: A
    }

    class IntRandomizer extends Randomizer[Int] {
      def draw = {
        import util.Random
        Random.nextInt()
      }
    }

    val intRand = new IntRandomizer

    intercept[ClassCastException] {
      intRand.asInstanceOf[String] //intRand cannot be cast to String
    }
  }

  koan("null.asInstanceOf[className] can be used to generate basic default values") {
    null.asInstanceOf[String] should be(null)
    null.asInstanceOf[Int] should be(0)
    null.asInstanceOf[Short] should be(0)
  }


  /* TODO: This probably needs to move to another category,
     TODO: since this class is supposed to be about type signatures  */
  koan("""Classes can be abstract. Abstract classes can define some methods
         |   concretely or may rely on it\'s subclasses to implement.
         |   If a method has no body and is in
         |   an abstract class, the method is considered abstract.""") {
    abstract class Parent {
      def add(x: Int): Int

      //this is considered abstract
    }

    class Child extends Parent {
      def add(x: Int): Int = x + 3
    }

    new Child().add(3) should be(6)
  }

  /* TODO:  This probably needs to move to another category,
     TODO:  since this class is supposed to be about type signatures  */
  koan("""Same koan as above. Except that concrete methods
         |   can have the modifier override to designate that it overrides a parent class.""") {
    abstract class Parent {
      def add(x: Int): Int

      //this is considered abstract
    }

    class Child extends Parent {
      override def add(x: Int): Int = x + 3

      //explicitly
    }

    new Child().add(3) should be(6)
  }

  class Fruit {
    override def toString = "Fruit"
  }

  class Citrus extends Fruit {
    override def toString = "Citrus"
  }

  class Orange extends Citrus {
    override def toString = "Orange"
  }

  class Tangelo extends Citrus {
    override def toString = "Tangelo"
  }

  class Apple extends Fruit {
    override def toString = "Apple"
  }

  class Banana extends Fruit {
    override def toString = "Banana"
  }


  koan("""Declaring +, indicates covariance variance.  Using + you can apply any
          |   container with a certain type to a container with a subclass of that type.
          |   This is known as a \"covariant\" position.""") {

    class MyContainer[+A](a: A) {
      private[this] var item = a

      def get = item

      //There is no setter see why in the next koan
    }

    val boxOfCitrus = new MyContainer[Citrus](new Orange)

    //Zen Moment: a box of citrus is a box of fruit
    val boxOfFruit: MyContainer[Fruit] = boxOfCitrus
  }

  koan("""Declaring covariance variance with + also means that the container cannot be mutable,
           | since that would cause type inconsistency.  Uncomment the offending lines
           | to compile and run this koan.""") {


    class MyContainer[+A](a: A) {
      private[this] var item = a

      def get = item

      //      def set(x: A) {       Uncomment this line
      //        this.item = x       Uncomment this line
      //      }                     Uncomment this line
    }

    val boxOfApples = new MyContainer[Apple](new Apple)

    //Zen Moment: We are calling a box of apples a box of fruit?
    val boxOfFruit: MyContainer[Fruit] = boxOfApples

    //Zen Moment: Just because someone calls it a box of fruit should we let them put an orange in our box of apples?
    //boxOfFruit.set(new Orange) //Uncomment this line

    val citrusBasket: MyContainer[Citrus] = new MyContainer[Citrus](new Orange)
    val fruitBasket: MyContainer[Fruit] = citrusBasket
    //    val bananaBasket: MyContainer[Banana] = citrusBasket //Uncomment this line
    //    val orangeBasket: MyContainer[Orange] = citrusBasket //Uncomment this line
    //    val tangeloBasket: MyContainer[Tangelo] = citrusBasket //Uncomment this line
    //    val appleBasket: MyContainer[Apple] = citrusBasket //Uncomment this line

    //    citrusBasket.set(new Orange)      //Uncomment this line
    //    citrusBasket.set(new Tangelo)     //Uncomment this line
    //    citrusBasket.set(new Apple)       //Uncomment this line
    //    citrusBasket.set(new Fruit)       //Uncomment this line
    //    citrusBasket.set(new Banana)      //Uncomment this line
    //    citrusBasket.set(new Citrus)      //Uncomment this line

    citrusBasket.get.toString should be("Orange")
    fruitBasket.get.toString should be("Orange")
  }

  koan("""Declaring -, indicates contravariance variance.  Using - you can apply any
          |   container with a certain type to a container with a superclass of that type
          |   This is known as a \"contravariant\" position. """) {

    class MyContainer[-A](a: A) {
      private[this] var item = a

      def set(a: A) {
        item = a
      }

      //No getter, see why in the next koan
    }

    val citrusBasket: MyContainer[Citrus] = new MyContainer[Citrus](new Orange)
    //val fruitBasket:MyContainer[Fruit] = citrusBasket //Uncomment this line
    //val bananaBasket:MyContainer[Banana] = citrusBasket //Uncomment this line
    val orangeBasket: MyContainer[Orange] = citrusBasket
    val tangeloBasket: MyContainer[Tangelo] = citrusBasket
    //val appleBasket: MyContainer[Apple] = citrusBasket //Uncomment this line

    citrusBasket.set(new Orange)
    citrusBasket.set(new Tangelo)
    //    citrusBasket.set(new Apple) //Uncomment this line
    //    citrusBasket.set(new Fruit) //Uncomment this line
    //    citrusBasket.set(new Banana) //Uncomment this line
    citrusBasket.set(new Citrus)
  }

  koan("""Declaring covariance variance with - also means that the container cannot be accessed,
           | since that would cause type inconsistency.  Uncomment the offending lines
           | to compile and run this koan.""") {

    class MyContainer[-A](a: A) {
      private[this] var item = a

      def set(a: A) {
        item = a
      }

      //def get = item   //Uncomment this line
    }

    val citrusBasket: MyContainer[Citrus] = new MyContainer[Citrus](new Orange)
    val orangeBasket: MyContainer[Orange] = citrusBasket
    val tangeloBasket: MyContainer[Tangelo] = citrusBasket

    //Zen moment: this is a tangelo basket but there is an orange in there? Would a 'get' make sense?
    //tangeloBasket.get.toString should be("Orange") //Uncomment this line
  }


  koan("""Declaring neither -/+, indicates invariance variance.  Using neither - or +,  you cannot apply any
          |   container with a superclass (\"contravariant\" position) or a subclass (\"covariant\" position)
          |   of that type""") {

    class MyContainer[A](a: A) {
      private[this] var item = a

      def set(a: A) {
        item = a
      }

      def get = item
    }

    //Creating a citrus basket
    val citrusBasket: MyContainer[Citrus] = new MyContainer[Citrus](new Orange)
    citrusBasket.set(new Orange)
    citrusBasket.get.toString should be("Orange")

    citrusBasket.set(new Tangelo)
    citrusBasket.get.toString should be("Tangelo")

    //  val fruitBasket:MyContainer[Fruit] = citrusBasket //Uncomment this line
    //  val bananaBasket:MyContainer[Banana] = citrusBasket //Uncomment this line
    //  val orangeBasket: MyContainer[Orange] = citrusBasket //Uncomment this line
    //  val tangeloBasket: MyContainer[Tangelo] = citrusBasket //Uncomment this line
    //  val appleBasket: MyContainer[Apple] = citrusBasket //Uncomment this line
    //  citrusBasket.set(new Apple) //Uncomment this line
  }



  //TODO: Do I need koans for overriding and subclassing?
  //TODO: Check if subclasses of  parents who implement traits still get traits
  //TODO:  koan("() => Unit is a type, and so is => Unit, and so is Int, Int => Int")
  //TODO: layer <: <: <:
  //TODO:Verify I got that right, even so this may not be koan material
  //TODO: Do we have anything for :_* to fit it into an Array, there was some trick I am forgetting.
}