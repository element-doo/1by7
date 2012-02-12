package hr.element.onebyseven
package ripper
package countries

import scala.concurrent.ops.future

trait CountryRipper {
  type T <: CountryStub
  def rip(): Seq[T]
}

object CountryRipper extends Ripper {
  def rip() {
    val isoCountries = future {
      iso.ISORipper.rip()
    }

    val wikiCountries = future {
      wiki.WikiRipper.rip()
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
