package com.kolodiychuk.alex.hude.coder.core.tree

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.junit.jupiter.api.Assertions

object TreeBuilderSpek : Spek({

  describe("tree builder") {

    val builder = TreeBuilder

    it("throws error when trying to build tree from empty symbols") {
      val symbols = ""
      Assertions.assertThrows<Throwable>(Error::class.java) {
        builder.makeTree(symbols)
      }
    }

    it("builds a singleton tree with one leaffrom single symbol") {
      val symbols = "a"
      val expectedTree = Leaf("a")
      TreeAssertions.assertTreesEqual(expectedTree, builder.makeTree(symbols))
    }

    it("builds simple tree with root and two children from 2 symbols") {
      val symbols = "ab"
      val expectedTree = Tree("ab",
          Leaf("a"),
          Leaf("b")
      )
      TreeAssertions.assertTreesEqual(expectedTree, builder.makeTree(symbols))
    }

    it("""builds a tree with 1 leaf on the left side and
          a sub tree with 2 leafs on the right for 3 symbols""") {
      val symbols = "abc"
      val expectedTree = Tree("abc",
          Leaf("a"),
          Tree("bc",
              Leaf("b"),
              Leaf("c")
          )
      )
      TreeAssertions.assertTreesEqual(expectedTree, builder.makeTree(symbols))
    }

    it("builds a more complex tree") {
      val symbols = "abcdefg"
      val expectedTree = Tree("abcdefg",
          Tree("abc",
              Leaf("a"),
              Tree("bc",
                  Leaf("b"),
                  Leaf("c")
              )
          ),
          Tree("defg",
              Tree("de",
                  Leaf("d"),
                  Leaf("e")
              ),
              Tree("fg",
                  Leaf("f"),
                  Leaf("g")
              )
          )
      )
      TreeAssertions.assertTreesEqual(expectedTree, builder.makeTree(symbols))
    }

  }

})
