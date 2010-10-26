import sbt._

class FunctionalKoans(info: ProjectInfo) extends DefaultProject(info) {
  val mavenLocal = "Local Maven Repository" at "file://" + Path.userHome + "/.m2/repository"
}
