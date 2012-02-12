package hr.element.onebyseven
package ripper
package mimetypes
package ff

import common._
import dispatch._
import tagsoup.TagSoupHttp._

import scala.xml._
import hr.element.etb.Pimps._

import se.fishtank.css.selectors._
import parser.SelectorParser

object FFRipper extends MimeTypeRipper {
  type T = FFMimeType

  val ffURI =
    :/("www.freeformatter.com") / "mime-types-list.html"

  val Rows =
    SelectorParser.parse("#mime-types-list tbody tr") match {
      case SelectorParser.Success(selectorGroups) =>
        selectorGroups
      case SelectorParser.Failure(msg) =>
        sys.error("Parse error: " + msg)
    }

  def rip() =
    Http(ffURI </> { _.collect {
      case e: Elem =>
        for {
          row <- Selectors.query(Rows, e)
        } yield {
          val cells = row.child.take(3).map(_.text).toList
          val name :: ext :: mimeType :: Nil = cells
          new FFMimeType(ext, mimeType, name)
        }
      }.flatten
    }) toIndexedSeq
}
