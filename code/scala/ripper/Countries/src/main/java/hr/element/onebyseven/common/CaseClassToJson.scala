package hr.element.onebyseven.common
import net.liftweb.json._
import net.liftweb.json.JsonDSL._
//
//object CaseClassToJson {
//
//  def apply() = {}
//}

class CaseClassToJson {

  val arr=  (
      Country.values.map{
              x =>
                (("alpha2"      -> x.alpha2) ~
                    ("alpha3"   -> x.alpha3) ~
                    ("numeric3" -> x.numeric3) ~
                    ("WikiName" -> x.wikiName))
            }.toList
            )

  val jsstr = (("Countries" -> arr))

  val str = compact(render(jsstr)).split('}')

  println(compact(render(jsstr)))

}
