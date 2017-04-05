package com.kolodiychuk.alex.hude.counter.core

import java.io.File
import com.kolodiychuk.alex.hude.common.Normalizer.fix

object Analyzer {

  fun countFilesCharOccurrences(files: List<File>): Occurrences = files
      .map { countFileCharOccurrences(it) }
      .reduce()
      .sortedByValuesDesc()

  fun countFileCharOccurrences(file: File): Occurrences {
    val readLines = file
        .readLines()
    val list = readLines.map { countCharOccurrences(it) }
    val reduce = list
        .reduce()
    return reduce
        .sortedByValuesDesc()
  }

  fun countCharOccurrences(line: String): Occurrences = with(line, ::fix)
      .toLowerCase().toCharArray()
      .groupBy { it -> it }
      .mapValues { it.value.size }
      .sortedByValuesDesc()
}
