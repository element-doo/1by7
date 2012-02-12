package hr.element.onebyseven
package ripper
package mimetypes
package ff

class FFMimeType(
  extension: String,
  val name: String,
  val mimeType: String) extends MimeTypeStub(name) {

  override val toString =
    "%s:[%s,%s]"format(extension, name, mimeType)
}
