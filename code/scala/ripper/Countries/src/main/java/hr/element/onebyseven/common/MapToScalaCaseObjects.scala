//package hr.element.onebyseven.common
//
//class MapToScalaCaseObjects(name: String, fields: Map[String, String],
//    instances: Seq[Any]) {
//  val formated: String = {
//    val abstractClassFieldDefinition = {
//      var sb = new StringBuilder
//      val formatedKeyValuePair = ( key: String) => key + ": " + fields(key)
//      sb append formatedKeyValuePair(fields.keys.head)
//      for (key <- fields.keys.tail){
//       sb append ",\n    "
//       sb append formatedKeyValuePair(key)
//      }
//      sb.result
//    }
//    val header = {
//      val namePackageSep = name.lastIndexOf(".")
//      "package " + name.take(name.lastIndexOf(".")) +"\n\n" +
//        "abstract sealed class "+ name.takeRight(namePackageSep) +" (\n" +
//        abstractClassFieldDefinition + ")"
//    }
//    val formatedCaseObject = (r: Any) => {
//      "case object "
//
//    }
//    ""
//  }
//
//}