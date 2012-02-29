package temp.test
import scala.util.Random
import hr.ngs.templater.Configuration
import java.io.FileInputStream
import java.io.FileOutputStream

object test extends App {

  val a = new Random(2)
  val g = IndexedSeq(Row("1","2"))
  val b = for (b <- 1 to 10) yield Row(a.nextString(5), a.nextString(5))
  val e = g ++ b
  val os = new FileOutputStream("/home/marin/Documents/test.xlsx")
  val doc = Configuration.factory().open(
      new FileInputStream("/home/marin/Documents/template22.xlsx"),
      "xlsx",
      os)
  val temp = doc.templater()
  val tags = temp.tags()
  val fields = classOf[Row].getDeclaredFields().map(x =>
    x.getName())

    val notF = tags diff fields
//  tags.foreach(
//      x => if (fields.f)
//        temp.replace(x, "")
//        )

    notF.foreach(x => temp.replace(x,""))
  doc.process(e)
  doc.flush()

}

case class Row(Column1: String, Column2: String)