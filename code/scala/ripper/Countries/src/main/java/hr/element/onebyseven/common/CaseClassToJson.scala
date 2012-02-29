package hr.element.onebyseven.common

import net.liftweb.json._
import net.liftweb.json.JsonDSL._
import java.io.OutputStreamWriter
import java.io.FileOutputStream
import java.io.ByteArrayOutputStream
import java.io.OutputStream

class CaseClassToJson{//}(fileName: String) {

  val arr = (
    Country.values.map {
      x =>
        (("alpha2" -> x.alpha2) ~
          ("alpha3" -> x.alpha3) ~
          ("numeric3" -> x.numeric3) ~
          ("WikiName" -> x.wikiName))
    }.toList)

  val jsstr = (("Countries" -> arr))
  override val toString = Printer.pretty(render(jsstr))
  val str = compact(render(jsstr))
  val toByteArray = toString.getBytes()
}

//  val w = new OutputStreamWriter(new FileOutputStream(fileName))
//
//  w.write(str)
//  w.close()
