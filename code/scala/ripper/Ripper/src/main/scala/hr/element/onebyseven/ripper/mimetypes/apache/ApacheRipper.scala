package hr.element.onebyseven
package ripper
package mimetypes
package apache

import common._
import dispatch._

import scala.xml._

object ApacheRipper extends MimeTypeRipper {
  type T = ApacheMimeType

  val apacheURI =
    :/("svn.apache.org") /
      "viewvc" / "httpd" /"httpd" / "trunk" /
      "docs" / "conf" / "mime.types?view=co"

  val ApacheLine =
    """([-a-z]+/[-+.\w]+)\t+([-\w]+(?: [-\w]+)*)"""r

  def rip() =
    Http(apacheURI >- { _
      .split('\n')
      .filterNot(_ startsWith "#")
      .map{line =>
        val ApacheLine(mime, exts) = line
        new ApacheMimeType(mime, exts split ' ' toSet)
      }
    }) toIndexedSeq
}
