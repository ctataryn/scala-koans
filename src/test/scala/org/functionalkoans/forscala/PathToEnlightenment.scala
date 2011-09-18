package org.functionalkoans.forscala

import org.scalatest.Suite
import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith

@RunWith(classOf[JUnitRunner])
class PathToEnlightenment extends Suite {
  override def nestedSuites = List(new AboutAsserts,
    new AboutValAndVar,
    new AboutLiteralBooleans,
    new AboutLiteralNumbers,
    new AboutLiteralStrings,
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
    new AboutHigherOrderFunctions,
    new AboutPartiallyAppliedFunctions,
    new AboutPartialFunctions,
    new AboutForExpressions,
    new AboutEnumerations,
    new AboutEmptyValues,
    new AboutParentClasses,
    new AboutNamedAndDefaultArguments,
    new AboutInfixPrefixAndPostfixOperators,
    new AboutInfixTypes,
    new AboutAccessModifiers,
    new AboutTypeSignatures,
    new AboutTraits,
    new AboutImportsAndPackages,
    new AboutPreconditions,
    new AboutUniformAccessPrinciple,
    new AboutImplicits,
    new AboutInteroperability,
    new AboutManifests
  )
}
