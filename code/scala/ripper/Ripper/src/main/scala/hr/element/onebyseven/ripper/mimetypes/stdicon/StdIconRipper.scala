//package hr.element.onebyseven
//package ripper
//package mimetypes
//package stdicon
//
//import common._
//import dispatch._
//
////import dispatch.liftjson.Js._
////
////import net.liftweb.json.JsonParser
////import net.liftweb.json.JsonAST._
//
//object StdIconRipper extends MimeTypeRipper {
//  type T = StdIconMimeType
//
//  val stdIconURI =
//    :/("www.stdicon.com") / "mimetypes"
//
//  val ExtensionPattern =
//    """^\.([-.\w]+)"""r
//
//  def rip() = {
//    Http(stdIconURI ># { json =>
//      for {
//        JObject(elem) <- json
//        JField(dotExt, JString(mime)) <- elem
//      } yield {
//        val ExtensionPattern(ext) = dotExt
//        new StdIconMimeType(ext, mime)
//      }
//    })
//  }
//}
