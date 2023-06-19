package techtest

import org.scalamock.scalatest.MockFactory
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class WordCountSpec extends AnyFlatSpec with Matchers with MockFactory{

  val mockTranslator: Translator = stub[Translator]
  val word = "shoe"
  val word2 = "football"
  val word3 = "rugby"

  "Word Counter" should "be able to add a word" in {
    val subject = new WordCounter(mockTranslator)
    val isAdded = subject.add(word)

    assert(isAdded)
  }

  "Word Counter" should "handle an empty word" in {
    val subject = new WordCounter(mockTranslator)
    val isAdded = subject.add("")

    assert(isAdded === false)
  }

  "Word Counter" should "handle a null word" in {
    val subject = new WordCounter(mockTranslator)
    val isAdded = subject.add(null)

    assert(isAdded === false)
  }

  it should "be able to return the count of that word" in {
    val subject = new WordCounter(mockTranslator)
    (mockTranslator.translate _).when(word).returns(word)
    subject.add(word)

    val count = subject.getCount("shoe")

    assert(count === 1)
  }

  it should "be able to return the count of empty word" in {
    val subject = new WordCounter(mockTranslator)
    subject.add(word)

    val count = subject.getCount("")

    assert(count === 0)
  }

  it should "be able to return the count of null word" in {
    val subject = new WordCounter(mockTranslator)
    subject.add(word)

    val count = subject.getCount(null)

    assert(count === 0)
  }

  it should "be able to return the count of that word twice" in {
    val subject = new WordCounter(mockTranslator)
    (mockTranslator.translate _).when(word).returns(word)
    subject.add(word)
    subject.add(word)

    val count = subject.getCount("shoe")

    assert(count === 2)
  }

  it should "not allow the addition of non-alphabetic characters in word" in {
    val word = "sh0e"

    val subject = new WordCounter(mockTranslator)
    val isAdded = subject.add(word)

    assert(isAdded === false)
  }

  it should "allow addition of multiple words per add" in {
    val subject = new WordCounter(mockTranslator)
    (mockTranslator.translate _).when(word2).returns(word2)
    (mockTranslator.translate _).when(word3).returns(word3)

    val isAdded = subject.add(word2, word3)

    val count = subject.getCount(word2)
    val count2 = subject.getCount(word3)
    assert(isAdded === true)
    assert(count === 1)
    assert(count2 === 1)
  }

  it should "translate foreign words" in {
    val englishWord = "flower"
    val spanishWord = "flor"
    val germanWord = "blume"
    (mockTranslator.translate _).when(spanishWord).returns(englishWord)
    (mockTranslator.translate _).when(germanWord).returns(englishWord)
    (mockTranslator.translate _).when(englishWord).returns(englishWord)
    val subject = new WordCounter(mockTranslator)

    subject.add(englishWord, spanishWord, germanWord)

    val count = subject.getCount(englishWord)
    assert(count == 3)
  }

  it should "tolerate failure on add" in {
    val subject = new WordCounter(mockTranslator)
    val explosiveWord = "BOOM!"
    (mockTranslator.translate _).when(explosiveWord) throws new RuntimeException(explosiveWord)

    val isAdded = subject.add(explosiveWord)

    assert(isAdded === false)
  }
}
