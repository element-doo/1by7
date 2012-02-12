package hr.element.onebyseven
package ripper

trait Ripper {
  def rip(): Unit
}

import java.io.File

object Ripper {
  val root =
    new File(
      Ripper.getClass
        .getResource("/").getFile.tail
        .replaceFirst("(?<=/ripper)/(Ripper/.*?)$", "")
    )
}
