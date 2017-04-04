package com.kolodiychuk.alex.hude.coder.core.tree

open class Node(
    val value: String = "",
    val left: Node? = null,
    val right: Node? = null) {

  constructor(value: String, children: Pair<Node, Node>)
      : this(value, children.first, children.second)
}