package hr.element.onebyseven.ripper.mspm
import java.io.OutputStreamWriter
import java.io.FileOutputStream
import hr.element.doit.csv.CSVConfig
import java.io.Writer

object Entry extends App {

<<<<<<< HEAD
  if (RippMspm.pagenavigatorNotDetector) println("true")
  else println("false")
  //  val someFile = "centri.csv"
//  val csvconfig = CSVConfig.default
//  val w = new FileOutputStream(someFile)
//  val writer = csvconfig.getWriter(w)
//  RippMspm(writer)
//  w.close()
}
=======
//  if (RippMspm.pagenavigatorNotDetector) println("true")
//  else println("false")
  val someFile = "Institutions1.sql"
  val w = new OutputStreamWriter(new FileOutputStream(someFile))

  CsvToSQL(w)
  w.close()
}
>>>>>>> 765fa0f16d569e7955bcdff103a35eff5d68a458
