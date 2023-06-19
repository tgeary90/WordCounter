package model

import play.api.libs.json.Json


object Word {
  case class Word(value: String)

  implicit val wordWrites = Json.writes[Word]
  implicit val wordReads = Json.reads[Word]
}

