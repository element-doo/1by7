package hr.element.onebyseven
package ripper
package mimetypes
package apache

class ApacheMimeType (
    mimeType: String
  , val extensions: Set[String]) extends MimeTypeStub(mimeType) {

  override val toString =
    "%s:[%s]"format(mimeType, extensions mkString ",")
}
