package com.kolodiychuk.alex.hude.coder.core.tree

open class Tree(
    val value: String = "",
    val left: Tree? = null,
    val right: Tree? = null) {

  constructor(value: String, children: Pair<Tree, Tree>)
      : this(value, children.first, children.second)
}