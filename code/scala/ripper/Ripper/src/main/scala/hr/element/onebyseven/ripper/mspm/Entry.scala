package hr.element.onebyseven.ripper.mspm
import java.io.OutputStreamWriter
import java.io.FileOutputStream
import hr.element.doit.csv.CSVConfig
import java.io.Writer

object Entry extends App {

//  if (RippMspm.pagenavigatorNotDetector) println("true")
//  else println("false")
  val someFile = "Institutions1.sql"
  val w = new OutputStreamWriter(new FileOutputStream(someFile))

  CsvToSQL(w)
  w.close()
}