package hr.element.onebyseven
package countryripper
package export

import wiki.WikiCountry

import scala.actors.Futures.future
import scala.collection.immutable.TreeSet

import java.io._

object CSVExport extends Export {
  def apply(countries: Traversable[WikiCountry]) {

    val csvTemplate =
      CSVExport.getClass.getResourceAsStream("countries.csv")

    val tD = getDF().open(csvTemplate, "utf8",
      new FileOutputStream("R:/a.csv"))
    tD.process(countries)
    tD.flush()
  }
}
