package hr.element.post.api

import scala.xml.XML
import hr.element.etb.Pimps._
import scalax.io._
import java.lang.Exception

object PostApi {
  val ZipList = "mjestaRH.xml"

  val list = {
  }

  lazy val cities = {
    val body = Resource.fromClasspath(ZipList).byteArray
    val list = new String(body, "UTF-8").toElem
    list \\ "mjesto" map City.apply
  }

  def findCities(cityName: String) = {
    val name = cityName.toLowerCase

    cities.filter(c => c.nazivPu.toLowerCase == name || c.naselje.toLowerCase == name )
  }

  def getZip(cityName: String) = {
    findCities(cityName).headOption.map(_.brojPu)
  }

}