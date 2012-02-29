package hr.element.onebyseven.common

import java.lang.Class
import java.io.OutputStreamWriter
import java.io.FileOutputStream
import java.io.File
import java.io.Writer
import java.io.PrintWriter
import java.io.FileWriter

//todo add Traversable[values]
class EnumToCaseObject(s: String) {
  val out = new PrintWriter(new FileWriter(s));
  val w: Writer = new OutputStreamWriter(new FileOutputStream(s))
  val cValues = Country.values.toSeq
  val maxNameLength = cValues.maxBy(_.wikiName.length).wikiName.length
  val header  = "package hr.element.onebyseven.common.newCountries"
  val obj     = {
"""
object Country extends Traversable[Country] {
    val values = Seq(
    """+
"%s".format(allAlpha2s) +
""")
    private def parse(abbr: String) = values.find(_.alpha2 == abbr)
    def apply(c: String) = parse(c).getOrElse(sys.error("Country  does not exist!" format c))
    def unapply(c: String) =
      parse(c)
    def foreach[U]( f: Country => U) = values.foreach(f)
  }
"""
  }
  val absCls ="""
abstract sealed class Country (
    val alpha3: String,
    val numeric3:  String,
    val wikiName: String){
  val alpha2 = getClass.getSimpleName
  val isoName = wikiName.toUpperCase
}

"""

  val makeCaseObject =
    (c: Country) => "case object %s extends Country(\"%s\", \"%s\", \"%s\"%s)\n"
      .format(c.alpha2, c.alpha3, c.numeric3, c.wikiName, spaces(maxNameLength -c.wikiName.length))
  lazy val allAlpha2s = {
        val zip = cValues.zipWithIndex.groupBy(_._2 / 17).toList.sortWith( // TODO sort before indexing
            (x,y) => x._1  <  y._1)
        val first = zip.head._2
        first.head._1 + first.tail.map(x => ", " + x._1).mkString +
            zip.tail.map(x => ",\n    " + x._2.head._1 + x._2.tail.map(x => ", " + x._1).mkString ).mkString
  }

  val spaces:(Int => String) = (i: Int ) => if (i == 0 ) " " else spaces(i -1 ) + " "
  w.write(header)
  w.write(obj)
  w.write(absCls)
  Country.values.foreach(c => w.write(makeCaseObject(c)))
  w.close()

}