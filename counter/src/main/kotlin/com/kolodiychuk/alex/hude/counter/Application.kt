package com.kolodiychuk.alex.hude.counter

import com.kolodiychuk.alex.hude.counter.core.Analyzer.countFilesCharOccurrences
import com.kolodiychuk.alex.hude.counter.core.ArgumentsParser.getArgumentsMap
import com.kolodiychuk.alex.hude.counter.core.Files
import com.kolodiychuk.alex.hude.counter.core.Files.getFiles
import com.kolodiychuk.alex.hude.counter.core.outputFlag
import com.kolodiychuk.alex.hude.counter.core.serialize
import com.kolodiychuk.alex.hude.counter.core.sourcesFlag
import java.io.File

object Application {

  @JvmStatic
  fun main(arguments: Array<String>) {
    val argumentsMap = with(arguments, ::getArgumentsMap)
    val fileNames = argumentsMap[sourcesFlag]
    val outputDirectory = argumentsMap[outputFlag]?.get(0)
    if (fileNames != null && fileNames.isNotEmpty()) {
      val files = with(fileNames, ::getFiles)
      if (files.isNotEmpty()) analyze(files, outputDirectory)
      else println("No existing files among passed sources")
    } else println("No sources to analyze")
  }

  private fun analyze(files: List<File>, outputDirectory: String?) {
    val occurrences = with(files, ::countFilesCharOccurrences).serialize()
    println("Occurrences:\n$occurrences")
    val targetPath = Files.getTargetFilename(files, outputDirectory)
    with(File(targetPath)) {
      if (parent != null) {
        with(File(parent)) { if (!exists()) mkdirs() }
      }
      writeText(occurrences)
    }
    println("Finished writing occurrences to $targetPath")
  }

}
