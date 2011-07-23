import sbt._
import de.element34.sbteclipsify._

class ScalaKoansProject(info: ProjectInfo) extends DefaultProject(info) with IdeaProject with Eclipsify {
  
  //Repositories
  val scalaTools = "Scala-Tools Maven2 Repository" at "http://scala-tools.org/repo-releases"
  
  //Artifacts
  val junit = "junit" % "junit" % "4.4" % "test"
  val scalaTest = "org.scalatest" % "scalatest_2.9.0" % "1.6.1" % "test"

  override def testCompileOptions = super.testCompileOptions ++ Seq(Unchecked)
}
