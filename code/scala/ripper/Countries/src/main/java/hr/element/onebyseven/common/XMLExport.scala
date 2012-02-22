package hr.element.onebyseven
package common
import scala.xml._

import net.liftweb.util._
import Helpers._
import BindPlus._

class CountryXMLExport {

  val tplAttr =
    <countries>
      <country />
    </countries>

//          <country c:alpha2="HR" c:alpha3="HRV" c:numeric3="191" c:name="Croatia"/>


  val tplTag =
    <countries>
      <country>
        <alpha2>HR</alpha2>
        <alpha3>HRV</alpha3>
        <numeric3>191</numeric3>
        <name>Croatia</name>
      </country>
    </countries>

  def bindCountries = (n: NodeSeq) => {
    val countries = Country.values()
    countries.toSeq.flatMap{ c => (
      ( "* [alpha2]" #> c.alpha2
          & "* [alpha3]" #> c.alpha3
          & "* [numeric3]" #> c.numeric3
          & "* [wikiName]" #> c.wikiName)
      //n.bind("c",

      //( "alpha2" #> c.alpha2 )
      /*& "alpha3=HRV" #> c.alpha3
      & "numeric3 *" #> c.numeric3
      & "name *" #> c.wikiName*/
      )(n)
    }
  }

  def toXML = {
    ("country" #> bindCountries)(tplAttr)
  }

  val pp = new PrettyPrinter(80, 2)

  val a = toXML
  println(pp.formatNodes(a))

}
