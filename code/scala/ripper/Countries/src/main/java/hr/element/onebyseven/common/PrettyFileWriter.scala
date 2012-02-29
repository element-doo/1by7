package hr.element.onebyseven.common

import scala.xml.PrettyPrinter
import java.io.OutputStreamWriter
import java.io.FileOutputStream
import scala.xml.Node
import scala.xml.NodeSeq

trait PrettyFileWriter {

  val xmlVal: NodeSeq

  private val pp = new PrettyPrinter(80, 2)
  def toFile(fileName: String) {

    val w = new OutputStreamWriter(new FileOutputStream(fileName))
    w.write(pp.formatNodes(xmlVal))
    w.close()
  }
  val toByteArray = pp.formatNodes(xmlVal).getBytes

}