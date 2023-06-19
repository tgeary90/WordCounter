package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.libs.json.{JsValue, Json}
import play.api.mvc.{Action, AnyContent, BaseController, ControllerComponents}
import techtest.WordCounter

import javax.inject.Inject
import scala.concurrent.ExecutionContext


@Singleton
class WordCounterController @Inject()(val cc: ControllerComponents,
                                      val wordCounter: WordCounter)
                                     (implicit val ec: ExecutionContext)
  extends BaseController {
  override protected def controllerComponents: ControllerComponents = cc

  def add(word: String): Action[JsValue] = Action(parse.json) { implicit request =>
    val isAdded = wordCounter.add(word)
    Ok(Json.obj("word" -> ("Added word: " + isAdded)))
  }

  def get(word: String): Action[JsValue] = Action(parse.json) { implicit request =>
    val count = wordCounter.getCount(word)
    Ok(Json.obj("word" -> ("count: " + count)))
  }
}
