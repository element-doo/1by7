//package hr.element.onebyseven.common
//import java.lang.Class
//
//case class ObjectValueGetter[@specialize(AnyRef) T] { //}}(t: Class[T]) { //},val defs: Map[String, String]) {
//  //val a = t.getDeclaredFields
//
//  //val getC = classOf[T]
//
//  def className = this.getClass().getDeclaredFields().map(_.getName())
//  val fieldNames= (a: AnyRef) => a.getClass().getDeclaredFields()//(a: T) =>
//
//
//  def getAll(a: AnyRef) = {
//    val fields = a.getClass.getDeclaredFields()
//    for(field <- fields) yield {
//      field.setAccessible(true)
//      field.get(a)//.asInstanceOf[String]
//    }
//  }
////implicit def reflector(ref: AnyRef) = new {
////  def getV(name: String): Any = ref.getClass.getMethods.find(_.getName == name).get.invoke(ref)
////  def setV(name: String, value: Any): Unit = ref.getClass.getMethods.find(_.getName == name + "_$eq").get.invoke(ref, value.asInstanceOf[AnyRef])
////}
// /* def get(s: String, a: T) = {
//    defs.get(s) match {
//      case Some(x) =>
//        val field = t.getDeclaredFields().find(_.getName == s).get
//        field.setAccessible(true)
//        val value = field.get(a)
//        x match {
//          case "Int" => value.asInstanceOf[Int]
//          case "String" => value.asInstanceOf[String]
//        }
//      case None => None
//    }
//
//  }*/
//
//}

//
//class NiceObject[T <: AnyRef](x : T) {
//  def niceClass : Class[_ <: T] = x.getClass.asInstanceOf[Class[T]]
//}
//
//implicit def toNiceObject[T <: AnyRef](x : T) = new NiceObject(x)
