package com.kolodiychuk.alex.hude.common

import com.kolodiychuk.alex.hude.counter.core.flatten
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.junit.jupiter.api.Assertions

object FlatteningSpek : Spek({

  describe("flattening") {
    val array = arrayOf("one", "two", "three", "four")
    val list = listOf("one", "two", "three", "four")
    val nestedArr = arrayOf("two", "three", "four")
    val nestedList = listOf("two", "three", "four")

    it("flattens array with nested array") {
      Assertions.assertArrayEquals(array,
          arrayOf("one", nestedArr).flatten())
    }

    it("flattens array with nested list") {
      Assertions.assertArrayEquals(array,
          arrayOf("one", nestedList).flatten())
    }

    it("flattens list with nested array") {
      Assertions.assertEquals(list,
          listOf("one", nestedArr).flatten<String>())
    }

    it("flattens list with nested list") {
      Assertions.assertEquals(list,
          listOf("one", nestedList).flatten<String>())
    }
  }

})