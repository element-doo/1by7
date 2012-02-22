package hr.element.onebyseven.common

class ObjectValueGeter[T](t: Class[T],val defs: Map[String, String]) {
  val a = t.getDeclaredFields

  def getAll(a: T) {
    val fields = t.getDeclaredFields()

    //fields.flatMap(x => x )

  }

  def get(s: String, a: T) = {
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

  }

}