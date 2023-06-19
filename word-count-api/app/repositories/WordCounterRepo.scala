package repositories

import techtest.{Translator, WordCounter}

case class WordCounterRepo() {

  val translator = new Translator {
    override def translate(foreignWord: String): String = foreignWord
  }
  val repo = new WordCounter(translator)

  def add(word: String): Boolean = repo.add(word)

  def get(word: String): Long = repo.getCount(word)
}
