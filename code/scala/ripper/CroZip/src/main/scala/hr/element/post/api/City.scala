package hr.element.post.api
import scala.xml.Node

case class City(
      val id       : Int
    , val brojPu   : Int
    , val redBroj  : Int
    , val nazivPu  : String
    , val naselje  : String
    , val zupanija : String
    ){
}

object City {

  def apply(elem: Node) = {
    def s(tag: String) = (elem \\ tag).text
    def i(tag: String) = s(tag).toInt

    new City(
        id       = i("id"),
        brojPu   = i("brojPu"),
        redBroj  = i("redBroj"),
        nazivPu  = s("nazivPu"),
        naselje  = s("naselje"),
        zupanija = s("zupanija")
        )
  }
}