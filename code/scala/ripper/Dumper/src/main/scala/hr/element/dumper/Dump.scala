package hr.element.dumper
import hr.element.doit.csv.CSVConfig
import java.io.ByteArrayOutputStream
import java.nio.charset.Charset

object Dump {
  type Settings = Map[String, String]
  def apply(table: Table, config: Settings) = {

    val  doCSV = () => {
      val csvConfig = {
      val conD = config.get("delimiter") match {
                case Some(x)  => CSVConfig.setDelimiter(x)
                case None     => CSVConfig.setDelimiter(';')
      }
      val conQ = config.get("new line") match {
                case Some(x)  => conD.setNewLine(x)
                case None     => conD.setNewLine(';')
      }
      val conN = config.get("quote") match {
                case Some(x)  => conQ.setQuotes(x)
                case None     => conQ.setQuotes("\"")
      }
      val conE = config.get("encoding") match {
                case Some(x)  => conN.setEncoding(Charset.forName(x))
                case None     => conN.setEncoding(Charset.forName("UTF-8"))
      }
      conE
    }
    val byteArr = new ByteArrayOutputStream
    val w       = csvConfig.getWriter(byteArr)
    w.write(table.header.toArray)
    table.values.foreach(x => w.write(x.toArray))
    byteArr
    }
  }
}

case class Table(
    header: IndexedSeq[String]
  , values: IndexedSeq[IndexedSeq[String]])

case class Config(
    quote     : String
  , delimiter : String
  , newLine   : String
  , encoding  : Charset)