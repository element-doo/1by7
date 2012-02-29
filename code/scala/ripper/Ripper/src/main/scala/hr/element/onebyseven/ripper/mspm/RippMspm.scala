package hr.element.onebyseven
package ripper.mspm

import common._
import dispatch._
import tagsoup.TagSoupHttp._
import hr.element.etb.Pimps._
import scala.xml._
import java.io.OutputStream
import java.io.Writer
import hr.element.doit.csv.CSVWriter

object RippMspm {
  val tipovi = (
    <option value="300">Centri socijalne skrbi</option>
    <option value="469">Domovi za starije i nemoćne osobe (domovi čiji je osnivač županija)</option>
    <option value="470">Domovi za starije i nemoćne osobe (domovi drugih osnivača)</option>
    <option value="471">Domovi za psihički bolesne odrasle osobe (domovi čiji je osnivač Republika Hrvatska)</option>
    <option value="474">Domovi za psihički bolesne odrasle osobe (domovi drugih osnivača)</option>
    <option value="475">Domovi za tjelesno ili mentalno oštećene osobe (domovi čiji je osnivač Republika Hrvatska)</option>
    <option value="476">Domovi za tjelesno ili mentalno oštećene osobe (domovi drugih osnivača)</option>
    <option value="477">Domovi za djecu bez odgovarajuće roditeljske skrbi (domovi čiji je osnivač Republika Hrvatska)</option>
    <option value="478" >Domovi za djecu bez odgovarajuće roditeljske skrbi (domovi drugih osnivača)</option>
    <option value="479">Domovi za djecu s poremećajima u ponašanju (domovi čiji je osnivač Republika Hrvatska)</option>
      )
//  <option value="806">Ustanove socijalne skrbi</option>
  val zupanije = (
    <option value="0">Bjelovarsko-bilogorska županija</option>
    <option value="1">Brodsko-posavska županija</option>
    <option value="2">Dubrovačko-neretvanska županija</option>
    <option value="3">Grad Zagreb</option>
    <option value="4">Istarska županija</option>
    <option value="5">Karlovačka županija</option>
    <option value="6">Koprivničko-križevačka županija</option>
    <option value="7">Krapinsko-zagorska županija</option>
    <option value="8">Ličko-senjska županija</option>
    <option value="9">Međimurska županija</option>
    <option value="10">Osječko-baranjska županija</option>
    <option value="11">Požeško-slavonska županija</option>
    <option value="12">Primorsko-goranska županija</option>
    <option value="13">Sisačko-moslavačka županija</option>
    <option value="14">Splitsko-dalmatinska županija</option>
    <option value="15">Šibensko-kninska županija</option>
    <option value="16">Varaždinska županija</option>
    <option value="17">Virovitičko-podravska županija</option>
    <option value="18">Vukovarsko-srijemska županija</option>
    <option value="19">Zadarska županija</option>
    <option value="20">Zagrebačka županija</option>)

  val WikiURI =
    :/("en.wikipedia.org") / "wiki" / "ISO_3166-1"
  val URI = (typ: String, county: String) => {
    val a = typ + "?subtree=" +
      typ + "&name=&county=" + county + "&trazi.x=20&trazi.y=13&trazi=true"
    :/("www.mspm.hr") / ("content") / ("view") / ("full") / (a)
  }

  def apply(w: CSVWriter) = {
    implicit def getValue(n: Node): String = { n.attribute("value").get.mkString } // TODO extract value form node

    var h = 0

    tipovi.foreach(tip =>
      zupanije.foreach { zup =>
        Http(URI(tip, zup) </> { ns =>
          for {
            table <- ns \\ "table"
            adr <- table.attribute("class").toSeq
            if adr.text == "list adresar"
            tr <- table \ "tr"
            tds = tr \ "td"
            if tds.nonEmpty
          } {
            val output =
              w.write(
                (tip.text +:
                  zup.text +:
                  tds.map(x => x.text.trim)).toArray)
          }
        })

      })
  }
}