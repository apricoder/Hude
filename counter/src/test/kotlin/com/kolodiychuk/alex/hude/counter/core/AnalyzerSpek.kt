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

    val line = "Lorem ipsum dolor sit amet"
    val ignoreCaseLine = "small x occurrences sums with upper X"
    val lineWithPunctuation = "some lines, after all, should contain non letter characters..."

    it("counts occurrences of chars in multiple text files") {
      val occurrences = analyzer.countFilesCharOccurrences(files)
      Assertions.assertEquals(15, occurrences['t'])
      Assertions.assertEquals(10, occurrences['e'])
      Assertions.assertEquals(8, occurrences['s'])
      Assertions.assertEquals(7, occurrences['a'])
    }

    it("counts occurrences of chars in single text file") {
      val occurrences = analyzer.countFileCharOccurrences(files[0])
      Assertions.assertEquals(7, occurrences['t'])
      Assertions.assertEquals(6, occurrences['e'])
      Assertions.assertEquals(4, occurrences['s'])
      Assertions.assertEquals(3, occurrences['a'])
    }

    it("counts every char occurrences in line") {
      val occurrences = analyzer.countCharOccurrences(line)
      Assertions.assertEquals(2, occurrences['l'])
      Assertions.assertEquals(3, occurrences['o'])
      Assertions.assertEquals(4, occurrences[' '])
    }

    it("ignores case counting occurrences") {
      val occurrences = analyzer.countCharOccurrences(ignoreCaseLine)
      Assertions.assertEquals(2, occurrences['x'])
    }

    it("counts punctuation chars as well") {
      val occurrences = analyzer.countCharOccurrences(lineWithPunctuation)
      Assertions.assertEquals(2, occurrences[','])
      Assertions.assertEquals(3, occurrences['.'])
    }
  }
})
