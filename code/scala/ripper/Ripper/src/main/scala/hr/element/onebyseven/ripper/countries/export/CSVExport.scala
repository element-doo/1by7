package hr.element.onebyseven
package ripper
package countries
package export

import wiki.WikiCountry

import scala.concurrent.ops.future

import java.io._

object CSVExport extends Export {
  def apply(countries: Traversable[WikiCountry]) {

    val csvTemplate =
      CSVExport.getClass.getResourceAsStream("countries.csv")

    val outputFile =
      new File(
        Ripper.root,
        "Countries/src/test/resources/hr/element/onebyseven/common/test/countries.csv"
      )

    val tD = getDF().open(csvTemplate, "utf8",
      new FileOutputStream(outputFile))
    tD.process(countries)
    tD.flush()
  }
}
