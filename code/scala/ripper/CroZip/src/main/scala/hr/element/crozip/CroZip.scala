package hr.element.crozip

import scala.xml.XML
import hr.element.etb.Pimps._
import scalax.io._
import scalax.io.Codec.UTF8
import java.lang.Exception

object CroZip {
  val ZipList = "mjestaRH.xml"

  lazy val cities = {
    val body = Resource.fromClasspath(ZipList).string(UTF8).toElem
    body \\ "mjesto" map City.apply
  }

  def findCities(cityName: String) = {
    val name = cityName.toLowerCase
    cities.filter(c => c.nazivPu.toLowerCase == name || c.naselje.toLowerCase == name)
  }

  def getZip(cityName: String) = {
    findCities(cityName).headOption.map(_.brojPu)
  }
}
