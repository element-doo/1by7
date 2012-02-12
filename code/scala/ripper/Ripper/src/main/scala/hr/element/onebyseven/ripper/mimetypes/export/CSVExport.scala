package hr.element.onebyseven
package ripper
package mimetypes
package export

import scala.concurrent.ops.future

import java.io._

object CSVExport extends Export {
  def apply(mimeTypes: Traversable[ExtensionMimeType]) {

    val csvTemplate =
      CSVExport.getClass.getResourceAsStream("mimetypes.csv")

    val outputFile =
      new File(
        Ripper.root,
        "MimeTypes/src/test/resources/hr/element/onebyseven/common/test/mimetypes.csv"
      )

    val tD = getDF().open(csvTemplate, "utf8",
      new FileOutputStream(outputFile))
    tD.process(mimeTypes)
    tD.flush()
  }
}
