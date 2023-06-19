package controllers

import play.api.libs.json.{JsValue, Json}
import play.api.mvc.{Action, AnyContent, BaseController, ControllerComponents}

import javax.inject.Inject
import scala.concurrent.ExecutionContext


@Singleton()
class WordCounterController @Inject()(
                                         cc: ControllerComponents)(
                                         implicit val ec: ExecutionContext)
  extends BaseController {
  override protected def controllerComponents: ControllerComponents = cc

  def add(word: String): Action[JsValue] = Action(parse.json) { implicit request =>
    Ok(Json.obj("word" -> ("Added word" + word)))
  }
}
