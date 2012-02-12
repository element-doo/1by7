package hr.element.onebyseven
package ripper
package mimetypes
package ff

class FFMimeType(
  mimeType: String,
  val extensions: Set[String],
  val description: String) extends MimeTypeStub(mimeType) {

  override val toString =
    "%s:[%s - %s]"format(mimeType, extensions mkString ",", description)
}
