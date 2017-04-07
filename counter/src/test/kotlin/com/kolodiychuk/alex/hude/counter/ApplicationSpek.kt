package com.kolodiychuk.alex.hude.counter

import com.kolodiychuk.alex.hude.counter.core.flatten
import com.kolodiychuk.alex.hude.counter.core.outputFlag
import com.kolodiychuk.alex.hude.counter.core.sourcesFlag
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.xit
import java.io.File

object ApplicationSpek : Spek({

  describe("application") {

    val app = Application
    val testsRoot = CounterTests.getRootDirectory()
    val resources = "$testsRoot/src/test/resources"
    val mocks = "$resources/mocks"
    val paths = arrayOf(
        "$mocks/some.file.txt",
        "$mocks/some.other.file.txt")
    val unexistingPaths = arrayOf(
        "$mocks/some.imaginary.file",
        "$mocks/some.other.unexisting.file")
    val literature = "$resources/literature"
    val outputDirectory = "${File("").absolutePath}/out"

    it("prints info message when no sources passed") {
      app.main(arrayOf())
    }

    it("prints the same info message when passed sources flag but no sources") {
      app.main(arrayOf(sourcesFlag))
    }

    it("prints info message when no source from passed exists") {
      app.main(arrayOf(sourcesFlag, unexistingPaths).flatten())
    }

    /*
        change 'xit' to 'it' to run ignored tests
    */
    xit("counts occurrences of chars in test resources files") {
      app.main(arrayOf(sourcesFlag, paths).flatten())
    }

    xit("counts occurrences of chars in test resources files and writes them to output directory") {
      app.main(arrayOf(sourcesFlag, paths, outputFlag, outputDirectory).flatten())
    }

    xit("counts occurrences of real ukrainian literary works") {
      app.main(arrayOf(sourcesFlag, literature, outputFlag, "$outputDirectory/literature").flatten())
    }

  }

})
