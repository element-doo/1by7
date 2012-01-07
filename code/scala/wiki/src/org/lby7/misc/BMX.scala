package org.lby7.misc

import javax.xml.namespace._
import javax.xml.parsers._
import javax.xml.transform._
import javax.xml.xpath._

import java.io._
import scala.xml._
import stream._
import dom._

class XmlHelper( p: Elem ){
  def addChild( c:Elem ) = XmlHelper.addChild( p, c )
  def addChildren( cL:List[Elem] ) = (p /: cL){ XmlHelper.addChild }
}

object XmlHelper{
  private def addChild( p:Elem, c:Elem ) = p match{case e:Elem=>e.copy(child=e.child++c)}
  implicit def wrapXmlHelper( e: Elem ) = new XmlHelper( e )
}

object BMX{

   def initTransformer() = {
    val tF = TransformerFactory.newInstance()
    val t = tF.newTransformer
    t.setOutputProperty( OutputKeys.OMIT_XML_DECLARATION, "yes" )
    t.setOutputProperty(OutputKeys.INDENT, "yes")
    t
  }

  val t = initTransformer()

//  -----------------------------------------

  def initXPath() = {
    val xPF = XPathFactory.newInstance()
    xPF.newXPath()
  }

  val xp = initXPath()

//  -----------------------------------------

  def initDocumentBuilder() = {
     val dBF = DocumentBuilderFactory.newInstance()
     dBF.setValidating(false)
     dBF.setFeature("http://xml.org/sax/features/namespaces", false)
     dBF.setFeature("http://xml.org/sax/features/validation", false)
     dBF.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false)
     dBF.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false)
     dBF.setNamespaceAware( false )
     dBF.newDocumentBuilder()
  }

  val bld = initDocumentBuilder()

  def string2owdn( xmlString: String ) = bld.synchronized{
    bld.parse( new InputSource( new StringReader( xmlString ) ) )
  }

//  -----------------------------------------

  def apply( node: org.w3c.dom.Node ):BMX = t.synchronized{
    val sB = new StringWriter()
    t.transform( new DOMSource( node ), new StreamResult( sB ) )
    BMX( sB.toString() )
  }

  def apply( xml: NodeSeq ):BMX = apply( xml.toString )
}

case class BMX( s: String ) {

  protected lazy val node = BMX.string2owdn( s )

  private def getXPath( path: String, mode: QName ) = {
    BMX.xp.compile( path ).evaluate( node, mode )
  }

  override def toString() = s

//  -----------------------------------------

  def XN( path: String ) =
    BMX( getXPath( path, XPathConstants.NODE ).asInstanceOf[org.w3c.dom.Node] )

  def XT( path: String ) =
    getXPath( path, XPathConstants.STRING ).asInstanceOf[String]

  def XTO( path: String ) = {
    val text = XT( path )
    if ( text.isEmpty ) None else Some( text.trim )
  }

  def XC( path: String ) =
    getXPath( "count(" + path + ")", XPathConstants.NUMBER ).asInstanceOf[java.lang.Double].intValue

  def XNL( path: String ) = {
    val nL = getXPath( path, XPathConstants.NODESET ).asInstanceOf[org.w3c.dom.NodeList]
    for ( i <- 0 until nL.getLength ) yield BMX( nL.item( i ) )
  }

  def XTL( path: String ) = {
    val nL = getXPath( path, XPathConstants.NODESET ).asInstanceOf[org.w3c.dom.NodeList]
    for ( i <- 0 until nL.getLength ) yield { nL.item( i ).getTextContent }
  }
}
