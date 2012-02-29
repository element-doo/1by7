package hr.element.onebyseven
package common
import scala.xml._

import net.liftweb.util._
import Helpers._
import BindPlus._

class CountryXMLExport extends PrettyFileWriter{

  val countries = Country.values
  val tplAttr =
    <countries>
      <country />
    </countries>

  val tplTag =
    <countries>
      <country>
        <alpha2>HR</alpha2>
        <alpha3>HRV</alpha3>
        <numeric3>191</numeric3>
        <name>Croatia</name>
      </country>
    </countries>

  def bindCountryValuesToAtributes = (n: NodeSeq) => {
    val countries = Country.values
    countries.toSeq.flatMap{ c => (
      ( "* [alpha2]" #> c.alpha2
          & "* [alpha3]" #> c.alpha3
          & "* [numeric3]" #> c.numeric3
          & "* [wikiName]" #> c.wikiName))(n)
    }
  }


  def bindCountryValuesToTags = (n: NodeSeq) =>
    countries.toSeq.flatMap{ c =>
      ( "alpha2 *" #> c.alpha2
      & "alpha3 *" #> c.alpha3
      & "numeric3 *" #> c.numeric3
      & "name *" #> c.wikiName)(n)
    }

  def toXMLWithTags = {
    ("country" #> bindCountryValuesToTags)(tplTag)
  }

  def toXMLWithAttributes = {
    ("country" #> bindCountryValuesToAtributes)(tplAttr)
  }

  val xmlVal = toXMLWithAttributes


}
