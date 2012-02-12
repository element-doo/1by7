package hr.element.onebyseven
package ripper
package mimetypes
package export

import java.io._

object JavaExport extends Export {
  def apply(mimeTypes: Traversable[ExtensionMimeType]) {
    val javaTemplate =
      JavaExport.getClass.getResourceAsStream("MimeType.java")

    val outputFile =
      new File(
        Ripper.root,
        "MimeTypes/src/main/java/hr/element/onebyseven/common/MimeType.java"
      )

    val tD = getDF().open(javaTemplate, "utf8",
      new FileOutputStream(outputFile))

    case class TemplaterExtensionMimeType(
        enumeration: String,
        extension: String,
        mimeType: String
    )

    val StartsWithNon_AZ =
      """^([^_A-Z].*)"""r

    val ContainsStrangeness =
      """(.*)\W+(.*)"""r

    def sanitizeEnum(enum: String): String = enum match {
      case StartsWithNon_AZ(x) =>
        sanitizeEnum('_' + x)

      case ContainsStrangeness(x, y) =>
        sanitizeEnum(x + '_' + y)

      case x =>
        x
    }

    val templaterEMs =
      mimeTypes.map{ em =>
        TemplaterExtensionMimeType(
          sanitizeEnum(em.extension.toUpperCase)
        , em.extension
        , em.mimeType
        )
      }

    val maxEnumLength = templaterEMs.map(_.enumeration.length).max
    val maxExtLength =  templaterEMs.map(_.extension.length).max
    val maxMimeLength = templaterEMs.map(_.mimeType.length).max

    val tt = tD.templater()
    tt.resize(Array("enumeration"), mimeTypes.size)

    templaterEMs.foreach{em =>
      tD.process(em)

      tt.replace("enum.padding", " " * (maxEnumLength - em.enumeration.length))
      tt.replace("ext.padding",  " " * (maxExtLength  - em.extension.length))
      tt.replace("mime.padding", " " * (maxMimeLength - em.mimeType.length))

      tt.replace("eol", if (em != templaterEMs.last) "," else ";")
    }

    tD.flush()
  }
}
