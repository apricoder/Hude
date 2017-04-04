package com.kolodiychuk.alex.hude.counter.core

import com.kolodiychuk.alex.hude.counter.CustomAssertions
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

object ArgumentsParserSpek : Spek({

  describe("arguments parser") {
    val parser = ArgumentsParser
    val resources = """src\test\resources"""
    val paths = arrayOf(
        "$resources/some.file.txt",
        "$resources/some.other.file.txt")

    it("returns arguments map grouped by flags") {
      val arguments = arrayOf("-s", paths, "-o", "out").flatten<String>()
      val expectedMap = mapOf("-s" to paths, "-o" to arrayOf("out"))
      val argumentsMap = parser.getArgumentsMap(arguments)
      CustomAssertions.assertMapsEqual(expectedMap, argumentsMap)
    }

    it("returns empty sub-lists for single flags") {
      val arguments = arrayOf("-s", paths, "-o", "-x").flatten<String>()
      val expectedMap = mapOf("-s" to paths, "-o" to arrayOf(), "-x" to arrayOf())
      val argumentsMap = parser.getArgumentsMap(arguments)
      CustomAssertions.assertMapsEqual(expectedMap, argumentsMap)
    }
  }
})

