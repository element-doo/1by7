package hr.element.onebyseven
package ripper
package mimetypes
package ff

import common._

import dispatch._
import tagsoup.TagSoupHttp._

import hr.element.etb.Pimps._
import scala.xml._

object FFRipper extends MimeTypeRipper {
  type T = FFMimeType

  val ffURI =
    :/("www.freeformatter.com") / "mime-types-list.html"

  def rip() =
    Http(ffURI </> {ns =>
      println(ns)

      null
    })

    null
}
