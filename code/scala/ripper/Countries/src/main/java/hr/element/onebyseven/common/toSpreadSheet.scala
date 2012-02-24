package hr.element.onebyseven.common
import hr.ngs.templater.Configuration
import java.io.OutputStream
import java.io.FileInputStream

class ToSpreadSheet(os: OutputStream, fileName: String) {

  val a = Configuration.factory().open(
      new FileInputStream(fileName),
      "xlsx",
      os)
      a.process(Country)
      a.flush()


}