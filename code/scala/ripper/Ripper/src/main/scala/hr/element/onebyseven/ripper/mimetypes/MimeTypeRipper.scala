package hr.element.onebyseven
package ripper
package mimetypes

import scala.concurrent.ops.future

abstract class MimeTypeStub(val mimeType: String)

trait MimeTypeRipper {
  type T <: MimeTypeStub
  def rip(): Seq[T]
}

case class ExtensionMimeType(extension: String, mimeType: String)

object MimeTypeRipper extends Ripper {
  def rip() {
    val ffMimeTypes = future {
      ff.FFRipper.rip()
    }

    val stdIconMimeTypes = future {
      stdicon.StdIconRipper.rip()
    }

    val apacheMimeTypes = future {
      apache.ApacheRipper.rip()
    }

    val extensionMimes =
      ffMimeTypes()
        .flatMap(mt => mt.extensions.map(e => e -> mt.mimeType)) ++
      stdIconMimeTypes()
        .map(mt => mt.extension -> mt.mimeType) ++
      apacheMimeTypes()
        .flatMap(mt => mt.extensions.map(e => e -> mt.mimeType))

    val exportable = (
      extensionMimes
      groupBy(_._1)
      mapValues{ _
        .map(_._2)
        .groupBy(identity)
        .mapValues(_.length)
        .toList.sortBy(-_._2)
        .head._1
      }).map(em =>
        ExtensionMimeType(em._1, em._2)
      ).toIndexedSeq[ExtensionMimeType]
       .sortBy(_.extension)

    export.CSVExport(exportable)
    export.JavaExport(exportable)
  }
}
