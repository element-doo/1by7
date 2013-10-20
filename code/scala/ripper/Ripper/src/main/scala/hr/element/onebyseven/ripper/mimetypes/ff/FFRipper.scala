package hr.element.onebyseven
package ripper
package mimetypes
package ff

import common._
import dispatch._

import scala.xml._

object FFRipper extends MimeTypeRipper {
  type T = FFMimeType

  val ffURI =
    :/("www.freeformatter.com") / "mime-types-list.html"

  val ExtensionPattern =
    """^\.([-.\w]+)"""r

  val Rows =
    SelectorParser.parse("#mime-types-list tbody tr") match {
      case SelectorParser.Success(selectorGroups) =>
        selectorGroups
      case SelectorParser.Failure(msg) =>
        sys.error("Parse error: " + msg)
    }

  def rip() =
    Http(ffURI </> { _.collect { case e: Elem =>
      Selectors
        .query(Rows, e)
        .flatMap{ row =>
          val cells = row.child take(3) map(_.text) toList
          val desc :: mime :: ext :: Nil = cells

          val exts = ext
            .split(", ")
            .collect{ case ExtensionPattern(ep) => ep }
            .toSet

          mime
            .split(",")
            .map(m => new FFMimeType(m, exts, desc))
        }
    }}).flatten.toIndexedSeq
}
