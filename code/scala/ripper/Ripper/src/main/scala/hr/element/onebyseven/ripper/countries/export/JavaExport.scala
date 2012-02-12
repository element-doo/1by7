package hr.element.onebyseven
package ripper
package countries
package export

import wiki.WikiCountry

import scala.actors.Futures.future
import scala.collection.immutable.TreeSet

import java.io._

import hr.element.etb.Pimps._

object JavaExport extends Export {
  def apply(countries: Traversable[WikiCountry]) {

    val javaTemplate =
      JavaExport.getClass.getResourceAsStream("Country.java")

    val outputFile =
      new File(
        Ripper.root,
        "Countries/src/main/java/hr/element/onebyseven/common/Country.java"
      )

    val tD = getDF().open(javaTemplate, "utf8",
      new FileOutputStream(outputFile))

    val maxName = countries.map(_.name.length).max

    val tt = tD.templater()
    tt.resize(Array("name"), countries.size)
    countries.foreach{country =>
      tD.process(country)
      tt.replace("numeric3.padding", " " * (3 - country.n3.code.toShort.toString.length))
      tt.replace("numeric3", country.n3.code.toInt)
      tt.replace("name.padding", " " * (maxName - country.name.length))
      tt.replace("eol", if (country != countries.last) "," else ";")
    }

    tD.flush()
  }
}
