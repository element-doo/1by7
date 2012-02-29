package hr.element.onebyseven.common
import com.lowagie.text._
import com.lowagie.text.pdf.PdfWriter
import java.io.FileOutputStream
import com.lowagie.text.pdf.PdfTable
import com.lowagie.text.pdf.PdfPTable
import com.lowagie.text.pdf.PdfPCell
import java.io.ByteArrayOutputStream

object WarpediText{//}(fileName: String) {

  def makePdf() = {
    val ba = new ByteArrayOutputStream
    val doc = new Document(PageSize.A4)
    PdfWriter.getInstance(doc, ba)//new FileOutputStream(fileName))
    doc.open()
    doc.add(makeTable)
    doc.close()
    ba.toByteArray()
  }

  protected def makeTable() = {
    implicit def stringToPdfPCell(s: String) =  new PdfPCell(new Phrase(s))
    val table = new PdfPTable(4)
    Country.values.foreach{x =>
      table.addCell(x.alpha2)
      table.addCell(x.alpha3)
      table.addCell(x.numeric3)
      table.addCell(x.wikiName)}
    table
  }
}


