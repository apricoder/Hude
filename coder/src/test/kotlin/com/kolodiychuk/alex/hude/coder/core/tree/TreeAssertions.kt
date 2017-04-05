package com.kolodiychuk.alex.hude.coder.core.tree

import org.junit.jupiter.api.Assertions

object TreeAssertions {

  fun assertTreesEqual(expected: Tree, actual: Tree) {
    Assertions.assertEquals(expected.value, actual.value)
    Assertions.assertEquals(expected.value.length, actual.value.length)
    when (actual.value.length) {
      0 -> throw Error("Tree cannot have empty root node value")
      1 -> {
        Assertions.assertTrue(expected.left == null && actual.left == null)
        Assertions.assertTrue(expected.right == null && actual.right == null)
      }
      else -> {
        assertTreesEqual(expected.left!!, actual.left!!)
        assertTreesEqual(expected.right!!, actual.right!!)
      }
    }
  }


}