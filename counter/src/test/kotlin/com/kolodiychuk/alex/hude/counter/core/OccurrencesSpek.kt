package com.kolodiychuk.alex.hude.counter.core

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.junit.jupiter.api.Assertions

object OccurrencesSpek : Spek({

  describe("occurrences") {

    val occ_b2 = mapOf('b' to 2)
    val occ_a1_b2 = mapOf('a' to 1, 'b' to 1)
    val occ_a1_b3 = mapOf('a' to 1, 'b' to 3)
    val json_a1_b_3 = """{"a":1,"b":3}"""

    it("has extended reduce function") {
      Assertions.assertEquals(occ_a1_b3,
          listOf(occ_a1_b2, occ_b2).reduce())
    }

    it("can be serialized to json") {
      Assertions.assertEquals(json_a1_b_3,
          occ_a1_b3.serialize())
    }

    it("can be de-serialized from json") {
      Assertions.assertEquals(occ_a1_b3,
          json_a1_b_3.deserializeOccurrences())
    }

  }

})
