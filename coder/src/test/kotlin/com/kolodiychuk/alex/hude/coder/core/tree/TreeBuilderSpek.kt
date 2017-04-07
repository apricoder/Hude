package com.kolodiychuk.alex.hude.coder.core.tree

import com.kolodiychuk.alex.hude.common.ukrainianSymbols
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

    it("builds a singleton tree with one leaf from single symbol") {
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

    it("builds huffman tree for ukrainian language") {

      val tree = TreeBuilder.makeTree(ukrainianSymbols.joinToString(""))

      /* * * * * * * * * * * * * * * * * * * * * * * * * * * * *
       *
       *    Visual representation of bottom-left corner
       *    of tree built from calculated ukrainian symbols
       *    occurrences rating (space replaced with underscore)
       *
       *
       *                      ...                    ...
       *
       *
       *              /                 \
       *         (_оаи)                 (нвіт)
       *         /    \                 /    \
       *        /      \               /      \
       *     (_о)      (аи)         (нв)      (іт)
       *     /  \      /  \         /  \      /  \
       *   (_)  (о)  (а)  (и)     (н)  (в)  (і)  (т)
       *
       * * * * * * * * * * * * * * * * * * * * * * * * * * * * */


      /* Subtree with ' ', 'о', 'а', 'и' symbols (left on the graph) */
      Assertions.assertEquals(" ", tree.left!!.left!!.left!!.left!!.left!!.left!!.value)
      Assertions.assertEquals("о", tree.left!!.left!!.left!!.left!!.left!!.right!!.value)
      Assertions.assertEquals("а", tree.left!!.left!!.left!!.left!!.right!!.left!!.value)
      Assertions.assertEquals("и", tree.left!!.left!!.left!!.left!!.right!!.right!!.value)

      /* Subtree with 'н', 'в', 'і', 'т' symbols (right on the graph) */
      Assertions.assertEquals("н", tree.left!!.left!!.left!!.right!!.left!!.left!!.value)
      Assertions.assertEquals("в", tree.left!!.left!!.left!!.right!!.left!!.right!!.value)
      Assertions.assertEquals("і", tree.left!!.left!!.left!!.right!!.right!!.left!!.value)
      Assertions.assertEquals("т", tree.left!!.left!!.left!!.right!!.right!!.right!!.value)

      /* Next subtrees... */

    }

  }

})
