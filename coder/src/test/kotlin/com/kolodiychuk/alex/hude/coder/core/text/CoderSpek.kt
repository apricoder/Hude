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
      val source = "мама"
      val expected =
          /* м */ "001111" + /* а */ "000010" + /* м */ "001111" + /* а */ "000010"

      Assertions.assertEquals(expected, coder.sencode(source))
    }

    it("encodes sample sentence with non-letter symbols") {

      val source = "Чи закодується це речення (теза) правильно?"
      val expected = "" +
          /* ч */ "011001" + /* и */ "000011" + /*   */ "000000" + /* з */ "010011" +
          /* а */ "000010" + /* к */ "001100" + /* о */ "000001" + /* д */ "001110" +
          /* у */ "001101" + /* є */ "100010" + /* т */ "000111" + /* ь */ "010111" +
          /* с */ "001001" + /* я */ "010010" + /*   */ "000000" + /* ц */ "100001" +
          /* е */ "001000" + /*   */ "000000" + /* р */ "001011" + /* е */ "001000" +
          /* ч */ "011001" + /* е */ "001000" + /* н */ "000100" + /* н */ "000100" +
          /* я */ "010010" + /*   */ "000000" + /* ( */ "101100" + /* т */ "000111" +
          /* е */ "001000" + /* з */ "010011" + /* а */ "000010" + /* ) */ "101011" +
          /*   */ "000000" + /* п */ "010001" + /* р */ "001011" + /* а */ "000010" +
          /* в */ "000101" + /* и */ "000011" + /* л */ "001010" + /* ь */ "010111" +
          /* н */ "000100" + /* о */ "000001" + /* ? */ "100101"

      Assertions.assertEquals(expected, coder.sencode(source))
    }

    it("decodes single symbol") {
      Assertions.assertEquals("ч", coder.sdecode("011001"))
    }

    it("decodes 'mama' word") {
      val bits =
          /* м */ "001111" + /* а */ "000010" + /* м */ "001111" + /* а */ "000010"
      Assertions.assertEquals("мама", coder.sdecode(bits))
    }

    it("encodes and decodes text to compare if returned to same data") {
      val text = "Цей текст, доволі довгий, буде закодовано " + "\n" +
          "використовуючи мапу, приготовану на основі рейтингу " + "\n" +
          "появлень літер в українській мові. Розкодований " + "\n" +
          "код повинен дорівнювати оригінальному тексту. Регістр " + "\n" +
          "літер при цьому ігнорується."

      val encoded = coder.sencode(text)
      val decoded = coder.sdecode(encoded)

      println("* * * * * * * * * * * * * * * * * * * *")
      println("Text:\n$text\n\n")
      println("Encoded:\n${breakLines(encoded)}\n\n")
      println("Decoded:\n$decoded\n\n")
      println("* * * * * * * * * * * * * * * * * * * *")

      Assertions.assertEquals(text.toLowerCase(), decoded)
    }

  }

})

fun breakLines(line: String): String {
  val linesLength = 50
  val linesCount = line.length / linesLength
  return (0..linesCount).map {
    line.substring(it * linesLength, Math.min((it + 1) * linesLength, line.length)) + "\n"
  }.reduce { acc, line -> acc + line }
}
