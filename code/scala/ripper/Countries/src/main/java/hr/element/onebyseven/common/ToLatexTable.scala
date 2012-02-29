package hr.element.onebyseven.common
import hr.element.onebyseven.common
import java.io.OutputStreamWriter
import java.io.FileOutputStream
import java.lang.Class
import java.io.ByteArrayOutputStream

sealed abstract class options{//}(fileName: String){
  val makeLine = (values: Seq[String])
  => values.head + values.tail.map(" % " + _ ).mkString + " \\\\\n"

  val beginStatement: String
  val endStatement  : String

  val getHeaderArray = Seq("alpha2", "alpha3", "numeric3", "wikiName")
  val getValueArray = (c: Country) =>
    Seq(c.alpha2, c.alpha3, c.numeric3, c.wikiName)

  val mkHeader = makeLine(getHeaderArray)
  val mkLine = (c: Country ) => makeLine(getValueArray(c))

  lazy val toByteArray = {
      val ba = new ByteArrayOutputStream
      val w = new OutputStreamWriter(ba)
      w.write(beginStatement)
      w.write(mkHeader)
      Country.values.foreach(v => w.write(mkLine(v)))
      w.write(endStatement)
      val byteArray = ba.toByteArray()
      w.close()
      byteArray
  }
}

case class LatexTable extends options{
  val beginStatement  = "\\begin{tabular}{ c | c | c | c | }\n"
  val endStatement    = "\\end{tabular}\n"
}


