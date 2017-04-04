package com.kolodiychuk.alex.hude.counter.core

object ArgumentsParser {

  fun getArgumentsMap(arguments: Array<String>): Map<String, Array<String>> {
    val map = mutableMapOf<String, MutableList<String>>()
    var lastKey: String? = null

    arguments.forEach {
      if (it.startsWith("-")) {
        map[it] = mutableListOf()
        lastKey = it
      } else if (lastKey != null) {
        map[lastKey!!]?.add(it)
      }
    }

    return map.mapValues { it.value.toTypedArray() }
  }

}
