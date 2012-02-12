package hr.element.onebyseven
package ripper
package mimetypes
package ff

class FFMimeType(
  extension: String,
  val mimeType: String,
  val name: String) extends MimeTypeStub(name) {

  override val toString =
    "%s:[%s,%s]"format(extension, name, mimeType)
}
