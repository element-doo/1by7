package hr.element.onebyseven
package ripper

import scala.concurrent.ops.future

object EntryPoint {

  def main(args: Array[String]) {
    val rippers =
      Seq(
        countries.CountryRipper
      , mimetypes.MimeTypeRipper
      )

    val jobs = rippers.map{r =>
      future {
        r.rip()
      }
    }

    jobs.foreach(_())
  }
}
