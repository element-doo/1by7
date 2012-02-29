package hr.element.onebyseven.ripper.mspm
import java.io.OutputStreamWriter
import java.io.FileOutputStream
import hr.element.doit.csv.CSVConfig

object Entry extends App {
  val someFile = "centri.csv"
  val csvconfig = CSVConfig.default
  val w = new FileOutputStream(someFile)
  val writer = csvconfig.getWriter(w)
  RippMspm(writer)
  w.close()
}