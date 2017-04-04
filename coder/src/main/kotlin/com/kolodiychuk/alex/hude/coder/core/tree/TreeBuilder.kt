package com.kolodiychuk.alex.hude.coder.core.tree

object TreeBuilder {

  fun makeTree(symbols: String): Node {
    return when (symbols.length) {
      0 -> throw Error("Can't build tree from empty symbols list")
      1 -> Leaf(symbols[0].toString())
      2 -> Node(symbols, makeTree(symbols[0].toString()), makeTree(symbols[1].toString()))
      else -> Node(symbols, childTrees(symbols))
    }
  }

  private fun childTrees(symbols: String): Pair<Node, Node> {
    val mid = Math.floorDiv(symbols.length, 2)
    val leftTree = makeTree(symbols.substring(0, mid))
    val rightTree = makeTree(symbols.substring(mid))
    return Pair(leftTree, rightTree)
  }
}