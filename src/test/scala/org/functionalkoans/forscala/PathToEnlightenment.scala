package org.functionalkoans.forscala

import org.scalatest.Suite
import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith

@RunWith(classOf[JUnitRunner])
class PathToEnlightenment extends Suite {
  override def nestedSuites = List(new AboutAsserts,
    new AboutValAndVar,
    new AboutLiterals,
    new AboutConstructors,
    new AboutTuples,
    new AboutLists,
    new AboutMaps,
    new AboutSets,
    new AboutSequencesAndArrays,
    new AboutMutableMaps,
    new AboutMutableSets,
    new AboutOptions,
    new AboutPatternMatching,
    new AboutCaseClasses,
    new AboutPartiallyAppliedFunctions,
    new AboutPartialFunctions,
    new AboutNamedAndDefaultArguments,
    new AboutForExpressions,
    new AboutEnumerations,
    new AboutEmptyValues,
    new AboutParentClasses,
    new AboutAccessModifiers,
    new AboutTypeSignatures,
    new AboutTraits,
    new AboutImportsAndPackages,
    new AboutPreconditions,
    new AboutHigherOrderFunctions,
    new AboutUniformAccessPrinciple,
    new AboutImplicits,
    new AboutManifests
  )
}
