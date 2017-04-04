package com.kolodiychuk.alex.hude.counter.core

import java.io.File

object Analyzer {

  fun countFilesCharOccurrences(files: List<File>): Occurrences = files
      .map { countFileCharOccurrences(it) }
      .reduce()
      .sortedByValuesDesc()

  fun countFileCharOccurrences(file: File): Occurrences = file
      .readLines().map { countCharOccurrences(it) }
      .reduce()
      .sortedByValuesDesc()

  fun countCharOccurrences(line: String): Occurrences = line
      .toLowerCase().toCharArray()
      .groupBy { it -> it }
      .mapValues { it.value.size }
      .sortedByValuesDesc()
}
