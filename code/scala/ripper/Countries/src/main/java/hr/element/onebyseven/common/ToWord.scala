package hr.element.onebyseven.common
import hr.ngs.templater.Configuration
import java.io.FileInputStream
import java.io.OutputStream

case class ToWord(fileName: String, os: OutputStream) {

    val a = Configuration.factory().open(
      new FileInputStream(fileName),
      "docx",
      os)
      a.process(classOf[Country])
      a.flush()
}