package com.kolodiychuk.alex.hude.counter

import com.kolodiychuk.alex.hude.counter.core.flatten
import org.junit.jupiter.api.Assertions

object CustomAssertions {

  fun assertMapsEqual(expectedMap: Map<*, *>, argumentsMap: Map<*, *>) {
    Assertions.assertEquals(expectedMap.keys, argumentsMap.keys)
    Assertions.assertEquals(expectedMap.values.flatten<String>(),
        argumentsMap.values.flatten<String>())
  }

  fun assertIterableEqualsIgnoreOrder(expected: List<*>, actual: List<*>) {
    Assertions.assertTrue(expected.containsAll(actual) && actual.containsAll(expected))
  }

}