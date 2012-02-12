package hr.element.onebyseven
package ripper
package mimetypes
package stdicon

class StdIconMimeType(
    val extension: String
  , mimeType: String) extends MimeTypeStub(mimeType) {

  override val toString =
    "%s:[%s]"format(extension, mimeType)
}
