package com.kolodiychuk.alex.hude.counter.core

inline fun <reified T> Array<*>.flatten(): Array<T> = this.flatMap {
  com.kolodiychuk.alex.hude.counter.core.flatten<T>(it)
}.toTypedArray()

inline fun <reified T> Collection<*>.flatten(): Collection<T> = this.flatMap {
  com.kolodiychuk.alex.hude.counter.core.flatten<T>(it)
}

fun <T> flatten(it: Any?): List<T> {
  return when (it) {
    is Array<*> -> (it as Array<T>).toList()
    is Collection<*> -> it as List<T>
    else -> listOf(it as T)
  }
}

