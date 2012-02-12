package hr.element.onebyseven
package countryripper

import scala.actors.Futures.future
import java.io.StringWriter

object EntryPoint {
  def main(args: Array[String])  = {
    val isoCountries = future {
      iso.ISORipper.ripCountries()
    }

    val wikiCountries = future {
      wiki.WikiRipper.ripCountries()
    }

    assert(
      isoCountries().size == wikiCountries().size,
      "Downloaded country lists differ in size!"
    )

    val zip = wikiCountries() zip isoCountries()

    assert(
      zip.forall {
        case (wc, ic) =>
          wc.name.equalsIgnoreCase(ic.name)
      },
      "ISO names did not match Wiki names!"
    )

    export.CSVExport(wikiCountries())
    export.JavaExport(wikiCountries())
  }
}
