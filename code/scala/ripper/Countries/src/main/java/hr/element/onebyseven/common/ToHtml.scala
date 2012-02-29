package hr.element.onebyseven.common
import scala.xml._
import net.liftweb.util._
import Helpers._

import java.io.FileOutputStream
import java.io.OutputStreamWriter

class ToHtml(fileName: String) extends PrettyFileWriter {

  val tplAttr =
    <select>
      <option />
    </select>

  def bindCountry(c: Country) =
    ( "option *" #> c.wikiName
    & "* [value]" #> c.alpha2)

  def bindCountries(countries: Array[Country]) = (n: NodeSeq) =>
    countries.flatMap(bindCountry(_)(n)): Array[NodeSeq]

  val xmlVal =
    ("option" #> bindCountries(Country.toArray))(tplAttr)
}
