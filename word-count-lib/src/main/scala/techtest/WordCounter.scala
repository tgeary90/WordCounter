package techtest

import scala.collection.{SortedMap, mutable}

class WordCounter(translator: Translator) {
  val db = mutable.SortedMap[String, Long]().withDefaultValue(0)

  def getCount(word: String): Long = if (word == null || word.isEmpty) 0 else db(translator.translate(word))

  def add(word: String*): Boolean = {

    def addWord(word: String): Boolean = {
      if (db(word) > 0) {
        db(word) = db(word) + 1
      }
      else {
        db += (word -> 1)
      }
      true
    }

    def safeAdd(word: Seq[String]): Boolean = {
      if (word(0) == null || word(0).isEmpty) false else {

        val patt = "[^A-Za-z]".r

        val adds = for {
          w <- word
        } yield {
          patt.findFirstIn(w) match {
            case Some(_) => false
            case _ => addWord(translator.translate(w))
          }
        }
        adds.exists(identity)
      }
    }

     try {
       safeAdd(word)
     }
     catch {
       case e: RuntimeException => println(s"Could not add word $word"); false
     }
  }
}
