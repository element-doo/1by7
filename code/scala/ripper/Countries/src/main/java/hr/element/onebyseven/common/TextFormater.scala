package hr.element.onebyseven.common
import java.io.Writer
import java.io.OutputStreamWriter
import java.io.PrintWriter

class TextFormater(separation: Array[Int], w: Writer) {
  val count = Country.values()
  count.foreach{
      x =>
        val form = ( i: Int) => "%" + separation(i) + "s"

        w.write(form(0).format(x.alpha3))
        w.write(form(1).format(x.alpha2))
        w.write(form(2).format(x.numeric3))
        w.write(form(3).format(x.wikiName))
        w.write("\n")
  }
}

