package com.kolodiychuk.alex.hude.coder.core.text

import com.kolodiychuk.alex.hude.coder.core.tree.Tree
import com.kolodiychuk.alex.hude.coder.core.tree.TreeBuilder
import com.kolodiychuk.alex.hude.common.ukrainianSymbols
import java.util.*

class Coder(val tree: Tree = TreeBuilder.makeTree(ukrainianSymbols.joinToString(""))) {

   fun encode(text: String): BitSet {
     return BitSet()
   }

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

  fun decode(bits: BitSet): String {
    return ""
  }

  fun makeBitSet(bitsString: String): BitSet {
    val bitSet = BitSet(bitsString.length)
    bitsString.forEachIndexed { index, b ->
      when (b) {
        '1' -> bitSet.set(index)
      }
    }
    return bitSet
  }

}