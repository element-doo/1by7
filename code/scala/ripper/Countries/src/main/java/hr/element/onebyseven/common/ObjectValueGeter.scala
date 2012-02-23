package hr.element.onebyseven.common
import java.lang.Class

case class ObjectValueGetter[T <: AnyRef] { //}}(t: Class[T]) { //},val defs: Map[String, String]) {
  //val a = t.getDeclaredFields

  //val getC = classOf[T]
  
  val fieldNames = classOf[Class[T]].getDeclaredFields.map(_.getName())//(a: T) =>
  
  
  def getAll(a: T) = {
    val fields = a.getClass.getDeclaredFields()
    for(field <- fields) yield {
      field.setAccessible(true)
      field.get(a)//.asInstanceOf[String]
    }
  }

 /* def get(s: String, a: T) = {
    defs.get(s) match {
      case Some(x) =>
        val field = t.getDeclaredFields().find(_.getName == s).get
        field.setAccessible(true)
        val value = field.get(a)
        x match {
          case "Int" => value.asInstanceOf[Int]
          case "String" => value.asInstanceOf[String]
        }
      case None => None
    }

  }*/

}

//
//class NiceObject[T <: AnyRef](x : T) {
//  def niceClass : Class[_ <: T] = x.getClass.asInstanceOf[Class[T]]
//}
//
//implicit def toNiceObject[T <: AnyRef](x : T) = new NiceObject(x)
