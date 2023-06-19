package techtest

import org.scalamock.scalatest.MockFactory
import TestData._

trait TranslatorHelper extends MockFactory {
  val translator: Translator

  def getStubTranslator: Translator = {

    (translator.translate _).when(spanishWord).returns(englishWord)
    (translator.translate _).when(germanWord).returns(englishWord)
    (translator.translate _).when(englishWord).returns(englishWord)
    (translator.translate _).when(word).returns(word)
    (translator.translate _).when(word2).returns(word2)
    (translator.translate _).when(word3).returns(word3)
    (translator.translate _).when(explosiveWord) throws(new RuntimeException(explosiveWord))
    translator
  }
}
