package hr.element.onebyseven
package common
import java.io.FileOutputStream
import java.io.OutputStreamWriter
import java.io.Writer
import java.io.InputStreamReader
import hr.element.onebyseven.common.MysqlOption$



object trara extends App {

  ToSQL(new OutputStreamWriter(System.out), MysqlOption)

}

  //new toHtml()
  //new CountryXMLExport

//  val outputfile = new FileOutputStream("/home/marin/projects/Botswana.xlsx")
//  val fileName ="/home/marin/projects/someTeml.xlsx"
//  new ToSpreadSheet(outputfile, fileName)
//  outputfile.close()


//  val pp = new PrettyPrinter(80, 2)
//
//  val a = CountryXMLExport.toXML
//  println(pp.formatNodes(a))
//  new ToDb(new  ConnectionConfiguration("org.postgresql.Driver",
//    "postgresql",
//    "test_00",
//    "localhost",
//    "5432",
//    "test",
//    ""))