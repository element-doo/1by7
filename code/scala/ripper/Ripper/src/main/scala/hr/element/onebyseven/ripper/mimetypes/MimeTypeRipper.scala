package hr.element.onebyseven
package ripper
package mimetypes

import scala.concurrent.ops.future

trait MimeTypeRipper {
  type T <: MimeTypeStub
  def rip(): Seq[T]
}

object MimeTypeRipper extends Ripper {
  def rip() {
    val ffMimeTypes = future {
      ff.FFRipper.rip()
    }

    val mimeTypes = ffMimeTypes()

    export.CSVExport(mimeTypes)
    export.JavaExport(mimeTypes)
  }
}
