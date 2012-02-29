package hr.element.onebyseven.common
import hr.ngs.templater.Configuration
import java.io.OutputStream
import java.io.FileInputStream
import java.io.ByteArrayOutputStream

class ToSpreadSheet(template: String) {

  val ba = new ByteArrayOutputStream
  val a = Configuration.factory().open(
      new FileInputStream(template)
    , "xlsx"
    , ba)
  a.process(Country)
  a.flush()
  val toByteArray = ba.toByteArray
  ba.close()
}