package com.kolodiychuk.alex.hude.counter.core

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.junit.jupiter.api.Assertions

object MapsSpek : Spek({

  describe("maps") {

    val map = mapOf('a' to 2, 'b' to 3, 'c' to 1)

    it("sorts map by values") {
      Assertions.assertEquals(mapOf('c' to 1, 'a' to 2, 'b' to 3), map.sortedByValues())
    }

    it("sorts map by values descending") {
      Assertions.assertEquals(mapOf('b' to 3, 'a' to 2, 'c' to 1), map.sortedByValuesDesc())
    }
  }

})