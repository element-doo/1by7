package hr.element.onebyseven
package common
import java.io.FileOutputStream
import java.lang.Class

object trara {

  def main(args :Array[String]) {

    val b =  WarpediText.makePdf()
    b.foreach(println)
    println(b.length+"|" + new String(b,"UTF8"))



//    val someFile = "/home/marin/projects/County.json"
//    println(new CaseClassToJson(someFile).toString)
    //EnumToCaseObject(someFile)
 }
}

// val outputfile = new FileOutputStream("/home/marin/projects/Bot2.xlsx")
//  val fileName ="/home/marin/projects/someTeml.xlsx"
//  new ToSpreadSheet(outputfile, fileName)
//  outputfile.close()
//word    val fileOut = new FileOutputStream("/home/marin/projects/temp.docx")
//    ToWord("/home/marin/projects/wordTemplate.docx", fileOut)
//    fileOut.close()
  //Country.values.foreach(x => println(x.alpha2+" "+ x.alpha3+ " " +x.numeric3+" " + x.wikiName))

    //WarpediText("/home/marin/projects/Countries.pdf").makePdf
    //a.makePdf()
//
//
//    val cla = ObjectValueGetter[Country]
//    cla.className.foreach((println(_)))
//    (new CaseClassToJson).str.foreach(println)


//   ObjectValueGetter[Country].getAll(AF).foreach( println)// fieldNames.foreach(println)
//
    //LatexTable("/home/marin/projects/1by7/countires.tex")

//location  /home/marin/projects/1by7/code/scala/ripper/Countries/src/main/java/hr/element/onebyseven/common/Country.java
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
