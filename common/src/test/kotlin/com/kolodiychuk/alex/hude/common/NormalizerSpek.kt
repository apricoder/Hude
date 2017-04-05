package com.kolodiychuk.alex.hude.common

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.junit.jupiter.api.Assertions

object NormalizerSpek : Spek({

  describe("text normalizer") {

    val normalizer = Normalizer

    it("replaces custom quotes with common ones") {
      val before = """Якийсь «текст» із дивними ”лапками“ які ’іноді’ зустрічаються в літературі"""
      val after = """Якийсь "текст" із дивними "лапками" які 'іноді' зустрічаються в літературі"""
      Assertions.assertEquals(after, normalizer.fix(before))
    }

    it("replaces underscores with spaces") {
      val before = """Зустрічали_колись_ці_підкреслення_в_літературі?"""
      val after = """Зустрічали колись ці підкреслення в літературі?"""
      Assertions.assertEquals(after, normalizer.fix(before))
    }

    it("replaces fancy dashes with hyphens") {
      val before = """Різниця між цими тире – невелика, чому б не замінити їх – дефісом?"""
      val after = """Різниця між цими тире - невелика, чому б не замінити їх - дефісом?"""
      Assertions.assertEquals(after, normalizer.fix(before))
    }

    it("replaces tabs with 4 spaces") {
      val before = "Відступи\tзамістяться\tчотирма\tпробілами"
      val after = "Відступи    замістяться    чотирма    пробілами"
      Assertions.assertEquals(after, normalizer.fix(before))
    }

    it("replaces single 3 dots symbol with 3 dots") {
      val before = "Думи мої, думи мої…"
      val after = "Думи мої, думи мої..."
      Assertions.assertEquals(after, normalizer.fix(before))
    }

    it("removes all unexpected chars from text") {

      val before = """
      Мапа Хофмана (Huffman Code) буде будуватися із врахуванням [only]
      українських та суміжних символів, ігноруючи наприклад латиницю (latin alphabet)"""

      val after = """
      Мапа Хофмана ( ) буде будуватися із врахуванням []
      українських та суміжних символів, ігноруючи наприклад латиницю ( )"""

      Assertions.assertEquals(after, normalizer.fix(before))
    }

  }

})