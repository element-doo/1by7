package hr.element.onebyseven.common
import hr.ngs.templater.Configuration
import java.io.FileInputStream
import java.io.OutputStream
import java.io.ByteArrayOutputStream

class ToWord(fileName: String) {//}, os: OutputStream) {
    val os = new ByteArrayOutputStream
    val a = Configuration.factory().open(
      new FileInputStream(fileName),
      "docx",
      os)
    a.process(Country)
    a.flush()
    val toByteArray = os.toByteArray
    os.close()
}
