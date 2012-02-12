package hr.element.onebyseven
package countryripper
package wiki

import common._

import dispatch._
import tagsoup.TagSoupHttp._

import hr.element.etb.Pimps._
import scala.xml._

object WikiRipper extends Ripper[WikiCountry]{
  val WikiURI =
    :/("en.wikipedia.org") / "wiki" / "ISO_3166-1"

  def ripCountries() =
    Http(WikiURI </> {ns =>
      for {
        table <- ns \\ "table"
        th <- table \ "tr" \ "th"
        if th.toString.contains("ISO English country names")
        tr <- table \ "tr"
        tds = (tr \ "td").toIndexedSeq
        if tds.nonEmpty
      } yield {
        val name = tds(0) \\ "a" text
        val a2 = Alpha2(tds(1) text)
        val a3 = Alpha3(tds(2) text)
        val n3 = Numeric3(tds(3) text)
        new WikiCountry(name, a2, a3, n3)
      }
    }) toIndexedSeq
}
