package hr.element.onebyseven.common
import java.io.OutputStreamWriter
import java.io.FileOutputStream
import java.lang.Class

sealed abstract class options(fileName: String){
  val makeLine = (values: Seq[String])
  => values.head + values.tail.map(" % " + _ ).mkString + " \\\\\n"

  val getHeaderArray = Seq("alpha2", "alpha3", "numeric3", "wikiName")//ObjectValueGetter[Country].fieldNames
  val getValueArray = (c: Country) =>
    Seq(c.alpha2, c.alpha3, c.numeric3, c.wikiName)
  val mkHeader = makeLine(getHeaderArray)
  val mkLine = (c: Country ) => makeLine(getValueArray(c))
}
case class LatexTable(s: String) extends options(s){
  val w = new OutputStreamWriter(new FileOutputStream(s))
  val beginStatement  = "\\begin{tabular}{ c | c | c | c | }\n"
  val endStatement    = "\\end{tabular}\n"

  w.write(beginStatement)
  w.write(mkHeader)
  Country.values.foreach(v => w.write(mkLine(v)))
  w.write(endStatement)
  w.close()
  }


//object LExport {
//  def to(t: options) = t//(fileName)(fileName: String)
//}
