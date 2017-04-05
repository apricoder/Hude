package com.kolodiychuk.alex.hude.coder.core.tree

object TreeBuilder {

  fun makeTree(symbols: String): Tree {
    return when (symbols.length) {
      0 -> throw Error("Can't build tree from empty symbols list")
      1 -> Leaf(symbols[0].toString())
      2 -> Tree(symbols, makeTree(symbols[0].toString()), makeTree(symbols[1].toString()))
      else -> Tree(symbols, childTrees(symbols))
    }
  }

  private fun childTrees(symbols: String): Pair<Tree, Tree> {
    val mid = Math.floorDiv(symbols.length, 2)
    val leftTree = makeTree(symbols.substring(0, mid))
    val rightTree = makeTree(symbols.substring(mid))
    return Pair(leftTree, rightTree)
  }
}