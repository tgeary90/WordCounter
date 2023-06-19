package controllers

import model.Word._

import javax.inject._
import play.api.libs.json.{JsSuccess, JsValue, Json}
import play.api.mvc.{Action, AnyContent, BaseController, ControllerComponents}
import repositories.WordCounterRepo

import javax.inject.Inject
import scala.concurrent.ExecutionContext


@Singleton
class WordCounterController @Inject()(val cc: ControllerComponents,
                                      val wordCounter: WordCounterRepo)
                                     (implicit val ec: ExecutionContext)
  extends BaseController {
  override protected def controllerComponents: ControllerComponents = cc

  def add: Action[JsValue] = Action(parse.json) { implicit request =>

    val word = request.body.validate[Word] match {
      case JsSuccess(value, _) => value
    }
    val isAdded = wordCounter.add(word.value)
    Ok(Json.obj("word" -> (s"Added ${word}: " + isAdded)))
  }

  def get(word: String): Action[JsValue] = Action(parse.json) { implicit request =>
    val count = wordCounter.get(word)
    Ok(Json.obj("word" -> ("count: " + count)))
  }
}
