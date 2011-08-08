package org.functionalkoans.forscala

import org.scalatest.matchers.ShouldMatchers
import org.functionalkoans.forscala.support.KoanSuite

class AboutImportsAndPackages extends KoanSuite with ShouldMatchers {
  koan("An import can be placed in a method, hint this koan is a method") {
    import scala.collection.mutable.ArrayBuffer
    val arrayBuffer = ArrayBuffer.range(2, 10)
    arrayBuffer(0) should be(2)
    arrayBuffer(1) should be(3)
  }
}

class Artist(val firstName: String, val lastName: String)

package subpackage {

class AboutImportsAndPackagesInSubpackages extends KoanSuite with ShouldMatchers {
  koan("A package can be included in a file with an established established package, " +
          "and can encapsulate it's contents with a {} block") {
    val luther = new Artist("Luther", "Vandross")
    luther.lastName should be("Vandross")
  }
}

}

package album {

class Album(val name: String, val year: Short, val artist: Artist)

}

package media {


class AboutReferencingAbsolutePackages extends KoanSuite with ShouldMatchers {

  import org.functionalkoans.forscala.album.Album

  // <<< Note the import style
  koan("A import can be done based from absolute package heirarchy") {
    val stLouisBlues = new Album("St. Louis Blues", 1940, new Artist("Louie", "Armstrong"))
    stLouisBlues.getClass.getCanonicalName should be("org.functionalkoans.forscala.album.Album")
  }
}

class AboutReferencingAbsoluteRootPackages extends KoanSuite with ShouldMatchers {

  import _root_.org.functionalkoans.forscala.album.Album

  // <<< Note the import style
  koan("A import can be done based from absolute root package heirarchy using _root_") {
    val stLouisBlues = new Album("St. Louis Blues", 1940, new Artist("Louie", "Armstrong"))
    stLouisBlues.getClass.getCanonicalName should be("org.functionalkoans.forscala.album.Album")
  }
}

class AboutReferencingRelativePackages extends KoanSuite with ShouldMatchers {

  import album.Album

  // <<< Note the import style
  koan("A import can be done based from relative packaging") {
    val stLouisBlues = new Album("St. Louis Blues", 1940, new Artist("Louie", "Armstrong"))
    stLouisBlues.getClass.getCanonicalName should be("org.functionalkoans.forscala.album.Album")
  }
}

}

package music_additions {

class Genre(val name: String)

class Producer(val firstName: String, lastName: String)

class Distributor(val name: String)

}

class AboutImportingTechniques extends KoanSuite with ShouldMatchers {
  koan("To import all classes of a package, use _ as a wildcard") {
    import music_additions._
    val genre = new Genre("Jazz")
    val producer = new Producer("Joe", "Oliver")
    val distributor = new Distributor("RYKO Classic Music")

    genre.name should be("Jazz")
    producer.firstName should be("Joe")
    distributor.name should be("RYKO Classic Music")
  }

  koan("To import all classes of a package, use can also use {_} as a wildcard") {
    import music_additions.{_}
    val genre = new Genre("Jazz")
    val producer = new Producer("Joe", "Oliver")
    val distributor = new Distributor("RYKO Classic Music")

    genre.name should be("Jazz")
    producer.firstName should be("Joe")
    distributor.name should be("RYKO Classic Music")
  }

  koan("To import a select group of classes of a package, use {className1, className}") {
    import music_additions.{Genre, Distributor}
    val genre = new Genre("Jazz")
    val distributor = new Distributor("RYKO Classic Music")

    genre.name should be("Jazz")
    distributor.name should be("RYKO Classic Music")
  }

  koan("You can rename a class by using => and create an alias") {
    import music_additions.{Genre => MusicType, Distributor}
    val musicType = new MusicType("Jazz")
    val distributor = new Distributor("RYKO Classic Music")

    musicType.name should be("Jazz")
    distributor.name should be("RYKO Classic Music")
  }

  koan("You can rename a class by using =>, and also import all other classes in a package keeping their name") {
    import music_additions.{Genre => MusicType, _}
    val musicType = new MusicType("Jazz")
    val producer = new Producer("Joe", "Oliver")
    val distributor = new Distributor("RYKO Classic Music")

    musicType.name should be("Jazz")
    producer.firstName should be("Joe")
    distributor.name should be("RYKO Classic Music")
  }

  koan("You can also refuse classes from being imported using => _") {
    import music_additions.{Producer => _, _}
    val musicType = new Genre("Jazz")
    val distributor = new Distributor("RYKO Classic Music")

    musicType.name should be("Jazz")
    distributor.name should be("RYKO Classic Music")
  }

  koan("You can just import the package themselves,so you can give it a verbose identity") {
    import scala.collection.mutable
    val arrayBuffer = mutable.ArrayBuffer.range(2, 10) //sounds better: A Mutable ArrayBuffer
    arrayBuffer(0) should be(2)
    arrayBuffer(1) should be(3)
  }

  koan("You can just import the package themselves, and give it an alias!") {
    import scala.collection.{mutable => changeable}
    val arrayBuffer = changeable.ArrayBuffer.range(2, 10)
    arrayBuffer(0) should be(2)
    arrayBuffer(1) should be(3)
  }
}

