package org.functionalkoans.forscala
import org.scalatest.Suite
import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith

@RunWith(classOf[JUnitRunner])
class PathToEnlightenmentTest extends Suite {
  override def nestedSuites = List(new AboutAsserts,
                                   new AboutValAndVar,
				   new AboutConstructors,
				   new AboutForExpressions,
                                   new AboutNamedAndDefaultArguments,
                                   new AboutTuples,
                                   new AboutLists,
                                   new AboutMaps,
                                   new AboutSets,
                                   new AboutMutableMaps,
                                   new AboutMutableSets,
                                   new AboutPatternMatching,
                                   new AboutCaseClasses,
                                   new AboutOptions,
                                   new AboutSequencesAndArrays,
                   
                                   new AboutEmptyValues,
                                   new AboutParentClasses,
                                   new AboutLists,
                                   new AboutAccessModifiers,
                                   new AboutTypeSignatures,
                                   new AboutTraits,
                                   new AboutImportsAndPackages,
                                   new AboutPreconditions,
                                   new AboutHigherOrderFunctions,
                                   new AboutUniformAccessPrinciple

)

}
