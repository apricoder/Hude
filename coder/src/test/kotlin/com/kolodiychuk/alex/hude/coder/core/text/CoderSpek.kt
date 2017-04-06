package com.kolodiychuk.alex.hude.coder.core.text

import com.kolodiychuk.alex.hude.common.ukrainianSymbols
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.junit.jupiter.api.Assertions

object CoderSpek : Spek({

  describe("coder") {

    val coder = Coder()

    it("encodes single symbol") {
      Assertions.assertEquals("000000", coder.sencodeSymbol(' '))
    }

    it("prints all supported symbols and their bit representatives") {
      ukrainianSymbols.forEach {
        println("`${when (it) {
          '\n' -> "\\n"
          else -> "$it"
        }}` -> ${coder.sencodeSymbol(it)}")
      }
    }

    it("encodes 'mama' word") {

      /* * * * * * * * *
       * `м` -> 001111
       * `а` -> 000010
       * * * * * * * * */

      val source = "мама"
      val expected = "001111" + "000010" + "001111" + "000010"
      Assertions.assertEquals(expected, coder.sencode(source))
    }

    it("encodes sample sentence with non-letter symbols") {

      /* * * * * * * * *
       * `ч` -> 011001
       * `и` -> 000011
       * ` ` -> 000000
       * `з` -> 010011
       * `а` -> 000010
       * `к` -> 001100
       * `о` -> 000001
       * `д` -> 001110
       * `у` -> 001101
       * `є` -> 100010
       * `т` -> 000111
       * `ь` -> 010111
       * `с` -> 001001
       * `я` -> 010010
       * `ц` -> 100001
       * `е` -> 001000
       * `р` -> 001011
       * `н` -> 000100
       * `(` -> 101100
       * `)` -> 101011
       * `п` -> 010001
       * `в` -> 000101
       * `л` -> 001010
       * `?` -> 100101
       * * * * * * * * */

      val source = "Чи закодується це речення (теза) правильно?"
      val expected = "" +
          "011001" + "000011" + "000000" + "010011" + "000010" + "001100" + "000001" +
          "001110" + "001101" + "100010" + "000111" + "010111" + "001001" + "010010" +
          "000000" + "100001" + "001000" + "000000" + "001011" + "001000" + "011001" +
          "001000" + "000100" + "000100" + "010010" + "000000" + "101100" + "000111" +
          "001000" + "010011" + "000010" + "101011" + "000000" + "010001" + "001011" +
          "000010" + "000101" + "000011" + "001010" + "010111" + "000100" + "000001" +
          "100101"

      Assertions.assertEquals(expected, coder.sencode(source))
    }

  }

})
