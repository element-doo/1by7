//package hr.element.onebyseven.common
//import scala.xml._
//
//import net.liftweb.util._
//import Helpers._
//import BindPlus._
//
//class toHtml {
//
//  val tplAttr =
//    <select>
//      <option />
//    </select>
//
//  def bindCountry(c: Country) =
//  ( "option *" #> c.wikiName
//  & "* [value]" #> c.alpha2
//  )
//
//  def bindCountries(countries: Array[Country]) = (n: NodeSeq) =>
//    countries.flatMap(bindCountry(_)(n)): Array[NodeSeq]
//
//  def toXML =
//    ("option" #> bindCountries(Country.values.toIndexedSeq))(tplAttr)
//
//  val pp = new PrettyPrinter(80, 2)
//
//  val a = toXML
//  println( pp.formatNodes(a))
//
//}
