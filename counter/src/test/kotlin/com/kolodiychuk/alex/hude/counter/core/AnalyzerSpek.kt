package com.kolodiychuk.alex.hude.counter.core

import com.kolodiychuk.alex.hude.counter.CounterTests
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.junit.jupiter.api.Assertions

object AnalyzerSpek : Spek({

  describe("analyzer") {
    val analyzer = Analyzer

    val testsRoot = CounterTests.getRootDirectory()
    val mocks = "$testsRoot/src/test/resources/mocks"
    val files = Files.getExistingFiles(arrayOf(
        "$mocks/some.file.txt",
        "$mocks/some.other.file.txt"))

    val line = "Лорем іпсум долор сіт амет"
    val ignoreCaseLine = "появлення малого к сумуються із появленнями великого К"
    val lineWithPunctuation = "деякі рядки, зрештою, повинні містити небуквені символи..."

    it("counts occurrences of chars in multiple text files") {
      val occurrences = analyzer.countFilesCharOccurrences(files)
      Assertions.assertEquals(8, occurrences['т'])
      Assertions.assertEquals(8, occurrences['е'])
      Assertions.assertEquals(4, occurrences['с'])
      Assertions.assertEquals(2, occurrences['а'])
    }

    it("counts occurrences of chars in single text file") {
      val occurrences = analyzer.countFileCharOccurrences(files[0])
      Assertions.assertEquals(6, occurrences['т'])
      Assertions.assertEquals(4, occurrences['і'])
      Assertions.assertEquals(3, occurrences['е'])
      Assertions.assertEquals(2, occurrences['с'])
    }

    it("counts every char occurrences in line") {
      val occurrences = analyzer.countCharOccurrences(line)
      Assertions.assertEquals(2, occurrences['л'])
      Assertions.assertEquals(3, occurrences['о'])
      Assertions.assertEquals(4, occurrences[' '])
    }

    it("ignores case counting occurrences") {
      val occurrences = analyzer.countCharOccurrences(ignoreCaseLine)
      Assertions.assertEquals(3, occurrences['к'])
    }

    it("counts punctuation chars as well") {
      val occurrences = analyzer.countCharOccurrences(lineWithPunctuation)
      Assertions.assertEquals(2, occurrences[','])
      Assertions.assertEquals(3, occurrences['.'])
    }
  }
})
