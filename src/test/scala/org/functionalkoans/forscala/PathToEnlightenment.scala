package org.functionalkoans.forscala
import org.scalatest.Suite
import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith

@RunWith(classOf[JUnitRunner])
class PathToEnlightenment extends Suite {


  override def nestedSuites = List(new AboutAsserts,
                                   new AboutValAndVar,
				   new AboutConstructors,
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
                                   new AboutNamedAndDefaultArguments,
				   new AboutForExpressions,
                                   new AboutEmptyValues,
                                   new AboutParentClasses,
                                   new AboutAccessModifiers,
                                   new AboutTypeSignatures,
                                   new AboutTraits,
                                   new AboutImportsAndPackages,
                                   new AboutPreconditions,
                                   new AboutHigherOrderFunctions,
                                   new AboutUniformAccessPrinciple

)





}
