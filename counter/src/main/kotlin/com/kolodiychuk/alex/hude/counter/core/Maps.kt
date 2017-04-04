package com.kolodiychuk.alex.hude.counter.core

fun <K, V : Comparable<V>> Map<K, V>.sortedByValues(): Map<K, V> = this.toList().sortedBy { it.second }.toMap()

fun <K, V : Comparable<V>> Map<K, V>.sortedByValuesDesc(): Map<K, V> = this.toList().sortedBy { it.second }.reversed().toMap()