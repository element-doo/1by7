package org.lby7.iso

import org.lby7.misc.{Downloader, BMX}
import org.lby7.misc.TagSoupCleaner

object Countries {
  def update() {
    new CountryUpdater
  }
}

case class WikiName( name: String ){
  if ( name eq null ) throw new NullPointerException( "WikiName cannot be null!" )
  override val toString = name
}

import scala.collection.immutable.NumericRange

abstract class ISOCode( code: String, length: Int, chars: NumericRange[Char] ) extends Product {
  if ( code eq null ) throw new NullPointerException( productPrefix + " code was null!" )
  if ( code.length != length ) throw new IllegalArgumentException( productPrefix + " is not " + length + " characters: " + code )
  if ( !code.forall( chars.contains ) ) throw new IllegalArgumentException( productPrefix +
    " cannot contain characters other than " + chars.start + '-' + chars.end + ": " + code )
}

abstract class AlphaCode( code: String, length: Int ) extends ISOCode( code, length, 'A' to 'Z' )
abstract class NumericCode( code: String, length: Int ) extends ISOCode( code, length, '0' to '9' )

case class Alpha2( code: String ) extends AlphaCode( code, 2 ){
  override val toString = code
}

case class Alpha3( code: String ) extends AlphaCode( code, 3 ){
  override val toString = code
}

case class Numeric3( code: String ) extends NumericCode( code, 3 ){
  override val toString = code
}

import scala.collection.immutable.Map

case class WikiCountry( name: WikiName, a2: Alpha2, a3: Alpha3, n3: Numeric3 )

object WikiCountries{
  val URI = """http://en.wikipedia.org/wiki/ISO_3166-1"""

  def retrieve = {
    val doc = Downloader.cacheGet( URI, "iso-3166-1-[wiki].html" )

    val xml = BMX( TagSoupCleaner.fromString( doc ) )
    val rows = xml.XNL( "//table[2]//tr[position()>1]")

    rows.map{ c =>
      WikiCountry(
        WikiName( c XT "//td[1]/a | //td[1]/span[@class='sorttext']/a" ),
        Alpha2( c XT "//td[2]/a/tt" ),
        Alpha3( c XT "//td[3]/tt" ),
        Numeric3( c XT "//td[4]/tt" )
      )
    } toIndexedSeq
  }
}

case class ISOName( name: String ){
  if ( name eq null ) throw new NullPointerException( "ISOName cannot be null!" )
  if ( name.toUpperCase != name ) throw new IllegalArgumentException( "ISOName cannot contain lowercaes letters!" )
  override val toString = name
}

case class ISOCountry( name: ISOName, a2: Alpha2 )

object ISOCountries{
  val URL = """http://www.iso.org/iso/list-en1-semic-3.txt"""

  val retrieve = {
    val doc = Downloader.cacheGet( URL, "iso-3166-1-[iso].txt" )
    val rows = doc.split("""\r\n""").drop(2)

    rows.map{ c =>
      val name :: a2 :: Nil = c split ';' toList;
      ISOCountry( ISOName( name ), Alpha2( a2 ) )
    } toIndexedSeq
  }
}

import scala.actors.Futures.future
import org.apache.commons.io.FileUtils
import java.io.File

class CountryUpdater{
  val fWc = future{ WikiCountries.retrieve }
  val fIc = future{ ISOCountries.retrieve }

  val wc = Map.empty ++ fWc().map( c => c.a2 -> c )
  val ic = fIc().map( c => c.a2 -> c )

  val cts = ic.map{ kv =>
    val w = wc(kv._1)

    val iN = kv._2.name.toString
    val wN = w.name.toString

    if ( iN != wN.toUpperCase )
      throw new IllegalArgumentException( "ISOName and WikiName differ: (" + iN + " != " + wN + ")" )

    w
  }

  val maxName = cts.map( _.name.toString.length ).max

  val NL = "\r\n";

  val ae = cts.map{ c =>

    val a2 = c.a2.toString
    val a3 = c.a3.toString
    val n3 = c.n3.toString.toShort.toString
    val name = c.name.toString

    """    %%s( "%%s", (short) %%%ds, "%%s"%%%dc)""".
      format( 3, maxName - name.length + 1 ).
      format( a2, a3, n3, name, ' ' )
  }

  val enums = ae.mkString( "", ',' + NL, ';' + NL )

  val template = new File( "templates", "Country.java" )
  val java = FileUtils.readFileToString( template, "UTF-8" ).replace( "$(enums)", enums )



/*

  val tables = new File( "../../java/tables" )
  val output = new File( tables, "src/org/lby7/tables/Country.java" )
  val bin = new File( tables, "bin" )

  FileUtils.writeStringToFile( output, java, "UTF-8" )

  val javacParams = Array( "-classpath", "", "-d", bin.getCanonicalPath, output.getCanonicalPath )
  val res = com.sun.tools.javac.Main.compile( javacParams )

  if ( res != 0 )
    error( "Could not compile the " + output.getName + " table!" )

  val jarPath = new File( tables, "out/Countries.jar" )
  val classPath = new File( tables, "bin/org/lby7/tables/Country.class" )

  val jar = new sun.tools.jar.Main( System.out, System.err, "jar" )
  val jarParams = Array( "cf", jarPath.getCanonicalPath, classPath.getCanonicalPath )
  jarParams foreach println
  jar.run( jarParams )
*/
}
