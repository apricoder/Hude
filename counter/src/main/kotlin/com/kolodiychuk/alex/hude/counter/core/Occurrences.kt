package com.kolodiychuk.alex.hude.counter.core

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

typealias Occurrences = Map<Char, Int>
typealias SerializedOccurrences = String

fun List<Occurrences>.reduce() = this
    .reduce { acc, occ ->
      (acc.toList() + occ.toList()).map {
        val accumulated = acc[it.first] ?: 0
        val next = occ[it.first] ?: 0
        Pair(it.first, accumulated + next)
      }.toMap()
    }

fun Occurrences.serialize(): SerializedOccurrences = Gson()
    .toJson(this)

fun SerializedOccurrences.deserializeOccurrences(): Occurrences {
  return Gson().fromJson(this, genericType<Occurrences>())
}

inline fun <reified T> genericType(): Type
    = object : TypeToken<T>() {}.type

