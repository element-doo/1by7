package hr.element.onebyseven.common

class O(val i: Int, val o: Int)

object CaseObjectMaker {//extends App
  val i = new O(3, 7)
  classOf[O].getDeclaredFields().foreach{
      x =>
        x.setAccessible(true)
        println( x.getName()+" "+ x.get(i).asInstanceOf[Int])
        }
}




//
//  val count: Array[Country] = Country.values
//  val maxWikiName = count.maxBy(_.wikiName.length).wikiName.length
//  count.foreach{x =>
//
//    println("case object " +  x.alpha2 +
//        " extends Country(\""
//        + x.alpha3 +"\", "+
//        "%3s".format(""+new Integer(x.numeric3))
//        +", \""+
//        x.wikiName+ spacees(maxWikiName - x.wikiName.length) + "\")")
//
//
//  }
//          def spacees(i: Int): String =
//          if (i > 0) spacees(i - 1) + " "
//          else " "
//}
