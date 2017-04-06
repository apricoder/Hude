package com.kolodiychuk.alex.hude.common

object Normalizer {

  val replacements = mapOf(

                          /*------------------------------------------------------------*/
                          /*    symbol            alternative           occurrences     */
                          /*------------------------------------------------------------*/
      "\u2013" to "-",    /*    \u2013            - (hyphen)            43899           */
      "\t" to "    ",     /*    \t                '    ' (4 spaces)     37630           */
      "«" to "\"",        /*    «                 " (double quote)      5364            */
      "»" to "\"",        /*    »                 " (double quote)      5354            */
      "…" to "...",       /*    …                 ... (3 dots)          3414            */
      "\u00A0" to " ",    /*    \u00A0            ' ' (space)           3074            */
      "\u2013" to "-",    /*    \u2013            - (hyphen)            1342            */
      "“" to "\"",        /*    “                 " (double quote)      963             */
      "”" to "\"",        /*    ”                 " (double quote)      960             */
      "’" to "'",         /*    ’                 ' (single quote)      393             */
      "_" to " "          /*    _                 ' ' (space)           181             */
                          /*------------------------------------------------------------*/
  )

  fun fix(text: String): String {
    var normalized = text
    replacements.keys.forEach { normalized = normalized.replace(it, replacements[it]!!) }
    return normalized.filter { ukrainianSymbols.contains(it.toLowerCase()) }
  }

}