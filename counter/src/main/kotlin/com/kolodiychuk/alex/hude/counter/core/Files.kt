package com.kolodiychuk.alex.hude.counter.core

import java.io.File


object Files {

  /*
      Matches everything between # and .txt including.
      Example: occurrences#some-file-names{# (2).txt}
      Curly braced text will be found by regex
  */
  private val suffixRegex = Regex(""".+#.+(#.*\.txt)""")
  private val allowedSourceExtensions = arrayOf("txt", "md")
  private val sourcesInFilenameLimit = 3

  fun getFiles(paths: Array<String>): List<File> {
    val existing = getExistingFiles(paths)
    return mineFiles(existing.toTypedArray())
  }

  @Suppress("IMPLICIT_CAST_TO_ANY")
  fun mineFiles(files: Array<File>): List<File> = files
      .map {
        when {
          it.isDirectory -> mineFiles(it.listFiles())
          else -> if (allowedSourceExtensions.contains(it.extension)) it else null
        }
      }
      .filterNotNull()
      .flatten<File>()
      .toList()

  fun getExistingFiles(names: Array<String>): List<File> = names
      .map(::File)
      .filter(File::exists)

  fun getTargetFilename(sources: Collection<File>, outputDirectory: String? = ""): String {
    val directory = if (outputDirectory?.isNotEmpty() ?: false) "$outputDirectory${File.separator}" else ""
    val target = directory + getExpectedTargetFilename(sources)
    if (!File(target).exists()) return target
    else return getNextFreeNumberedPath(target)
  }

  private fun getNextFreeNumberedPath(target: String): String {
    var index = 1
    var file: File
    do {
      file = getNumberedCopy(target, index)
      index += 1
    } while (file.exists())
    return file.absolutePath
  }

  private fun getNumberedCopy(target: String, number: Int) =
      File(getNumberedFilename(target, number))

  fun getNumberedFilename(target: String, number: Int): String {
    val suffix = suffixRegex.matchEntire(target)?.groups?.get(1)?.value
    return target.replace(suffix!!, "#($number).txt")
  }

  private fun getExpectedTargetFilename(sources: Collection<File>): String {
    val sourceList = sources.map { it.name }.map { normalize(it) }
    val reducedSourcesList = sourceList
        .subList(0, Math.min(sourcesInFilenameLimit, sourceList.size))
        .reduce { acc, name -> "$acc.${normalize(name)}" } + (if (sourceList.size > sourcesInFilenameLimit) ".and-more" else "")
    return "occurrences-from#$reducedSourcesList#.txt"
  }

  fun normalize(name: String): String = File(name)
      .nameWithoutExtension
      .toLowerCase()
      .replace(" ", "-")
      .replace("_", "-")
      .replace(".", "-")
}
