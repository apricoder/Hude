package com.kolodiychuk.alex.hude.counter

import java.io.File


object CounterTests {

  fun getRootDirectory(): String {
    val runtimeRoot = File("").absolutePath
    val module = "counter"
    return if (runtimeRoot.endsWith(module)) runtimeRoot
    else "$runtimeRoot/$module"
  }

}