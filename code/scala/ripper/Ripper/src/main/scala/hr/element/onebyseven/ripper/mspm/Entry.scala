package hr.element.onebyseven.ripper.mspm
import java.io.OutputStreamWriter
import java.io.FileOutputStream
import hr.element.doit.csv.CSVConfig

object Entry extends App {

  if (RippMspm.pagenavigatorNotDetector) println("true")
  else println("false")
  //  val someFile = "centri.csv"
//  val csvconfig = CSVConfig.default
//  val w = new FileOutputStream(someFile)
//  val writer = csvconfig.getWriter(w)
//  RippMspm(writer)
//  w.close()
}