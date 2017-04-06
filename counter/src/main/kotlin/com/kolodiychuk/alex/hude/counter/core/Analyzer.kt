package com.kolodiychuk.alex.hude.counter.core

import java.io.File
import com.kolodiychuk.alex.hude.common.Normalizer.fix

object Analyzer {

  fun countFilesCharOccurrences(files: List<File>): Occurrences = files
      .map { countFileCharOccurrences(it) }
      .reduce()
      .sortedByValuesDesc()

  fun countFileCharOccurrences(file: File): Occurrences = file
      .readLines()
      .map { countCharOccurrences("$it\n") }
      .reduce()
      .sortedByValuesDesc()

  fun countCharOccurrences(line: String): Occurrences = with(line, ::fix)
      .toLowerCase().toCharArray()
      .groupBy { it -> it }
      .mapValues { it.value.size }
      .sortedByValuesDesc()
}
