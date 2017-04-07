package com.kolodiychuk.alex.hude.coder.core.text

import com.kolodiychuk.alex.hude.coder.core.tree.Tree
import com.kolodiychuk.alex.hude.coder.core.tree.TreeBuilder
import com.kolodiychuk.alex.hude.common.ukrainianSymbols

class Coder(val tree: Tree = TreeBuilder.makeTree(ukrainianSymbols.joinToString(""))) {

  fun sencode(symbols: String, tree: Tree = this.tree): String = symbols
      .map { sencodeSymbol(it, tree) }
      .reduce { acc, next -> acc + next }

  fun sencodeSymbol(symbol: Char, tree: Tree = this.tree): String =
      with(symbol.toLowerCase(), {
        if (tree.value == this.toString()) ""
        else when {
          tree.left != null && tree.left.value.contains(this) -> "0" + sencodeSymbol(this, tree.left)
          tree.right != null && tree.right.value.contains(this) -> "1" + sencodeSymbol(this, tree.right)
          else -> throw Error("Tree doesn't contain symbol!")
        }
      })

  fun sdecode(sbits: String, tree: Tree = this.tree): String {
    val decoded = mutableListOf<String>()
    var nextTree = tree
    sbits.forEach { bit ->

      val t = when (bit) {
        '0' -> nextTree.left
        '1' -> nextTree.right
        else -> throw Error("Invalid bit value")
      }

      if (t != null) {
        if (t.value.length == 1) {
          decoded.add(t.value)
          nextTree = tree
        } else nextTree = t
      } else throw Error("Can't decode - missing tree node")
    }

    return decoded.reduce { acc, c -> acc + c }
  }

}