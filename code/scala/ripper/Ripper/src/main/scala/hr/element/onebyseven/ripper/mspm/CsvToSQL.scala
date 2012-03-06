package hr.element.onebyseven.ripper.mspm
import java.io.Writer
import hr.element.doit.csv.CSVConfig
import java.io.FileInputStream

object CsvToSQL {
    val csvFile = "centri.csv"
    //val quoteString = (s: String) => "'" + s + "'"
    val getStringFromSeq = (s: Seq[String])
        =>"( " + s.reduce(_ + ", " + _) + ")"
    val insertStatement = (
      header: Seq[String]
    , values: Seq[String]
    , tableName: String) =>
      {
        "INSERT INTO " +
        tableName +
        getStringFromSeq(header)+
        " VALUES" +
        getStringFromSeq(values.map(x => "'"+x+"'"))+
          ";\n"
      }

    def getCountyCode(name: String) =
        Zupanija.values().find( name == _.naziv ).get.toString
    def getTypeCode(name: String) =
      tipovi.find(_.text == name).get.attribute("value").get.head text

    def getReader() = CSVConfig.default.getReader(new FileInputStream(csvFile))
    def apply(w: Writer){
      val headerIT = Seq("kod", "name")
      tipovi.foreach{n =>
          val insertValue = Seq(n.attribute("value").get.head text, n.text)
          val a = insertStatement(headerIT, insertValue, "InstitutionType")
          println(a)
          w.write(a)}
      val headerC = Seq("kod", "name")
      zupanije.foreach{n =>
          val name = n text
          val zup = getCountyCode(name)
          val insertValue = Seq(zup, name)
          w.write(insertStatement(headerC, insertValue, "County"))
          }
      val r = getReader()
      val headerI = Seq("type", "county", "name", "address", "phone")
      r.foreach{l =>
          val word = l.words.toSeq

          val values = Seq(
              getTypeCode(word(0))
            , getCountyCode(word(1))) ++ word.drop(2)

          w.write(insertStatement(headerI, values, "Institution"))
          }
      }

val tipovi = (
    <option value="300">Centri socijalne skrbi</option>
    <option value="469">Domovi za starije i nemoćne osobe (domovi čiji je osnivač županija)</option>
    <option value="470">Domovi za starije i nemoćne osobe (domovi drugih osnivača)</option>
    <option value="471">Domovi za psihički bolesne odrasle osobe (domovi čiji je osnivač Republika Hrvatska)</option>
    <option value="474">Domovi za psihički bolesne odrasle osobe (domovi drugih osnivača)</option>
    <option value="475">Domovi za tjelesno ili mentalno oštećene osobe (domovi čiji je osnivač Republika Hrvatska)</option>
    <option value="476">Domovi za tjelesno ili mentalno oštećene osobe (domovi drugih osnivača)</option>
    <option value="477">Domovi za djecu bez odgovarajuće roditeljske skrbi (domovi čiji je osnivač Republika Hrvatska)</option>
    <option value="478" >Domovi za djecu bez odgovarajuće roditeljske skrbi (domovi drugih osnivača)</option>
    <option value="479">Domovi za djecu s poremećajima u ponašanju (domovi čiji je osnivač Republika Hrvatska)</option>
      )

val zupanije = (
    <option value="0">Bjelovarsko-bilogorska županija</option>
    <option value="1">Brodsko-posavska županija</option>
    <option value="2">Dubrovačko-neretvanska županija</option>
    <option value="3">Grad Zagreb</option>
    <option value="4">Istarska županija</option>
    <option value="5">Karlovačka županija</option>
    <option value="6">Koprivničko-križevačka županija</option>
    <option value="7">Krapinsko-zagorska županija</option>
    <option value="8">Ličko-senjska županija</option>
    <option value="9">Međimurska županija</option>
    <option value="10">Osječko-baranjska županija</option>
    <option value="11">Požeško-slavonska županija</option>
    <option value="12">Primorsko-goranska županija</option>
    <option value="13">Sisačko-moslavačka županija</option>
    <option value="14">Splitsko-dalmatinska županija</option>
    <option value="15">Šibensko-kninska županija</option>
    <option value="16">Varaždinska županija</option>
    <option value="17">Virovitičko-podravska županija</option>
    <option value="18">Vukovarsko-srijemska županija</option>
    <option value="19">Zadarska županija</option>
    <option value="20">Zagrebačka županija</option>)
}