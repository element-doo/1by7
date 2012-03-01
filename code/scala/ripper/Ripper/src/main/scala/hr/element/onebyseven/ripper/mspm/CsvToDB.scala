package hr.element.onebyseven.ripper.mspm
import java.sql.DriverManager
import java.lang.Class
import java.util.Properties
import scala.xml.NodeBuffer

object CsvToDB {


  def apply(){
    //val sf = new DriverManager
    Class.forName("org.postgresql.Driver")
    val preps = new Properties();
    preps.put("user", "test")
    preps.put("password", "")

    val con = DriverManager.getConnection(
          "jdbc:postgresql:test_00://localhost:5432//", preps)
    prepareBase()


    insertThemAll(tipovi,)
    tipovi.foreach{tip =>
      val insertStatement =
        update(
          Array("value", "name")
        , Array(tip.attribute("value").get.mkString, tip text )
        , "types")
    }

    zupanije.foreach {zup =>
      val insertStatement =
        update(
          Array("value", "name")
        , Array(zup.attribute("value").get.mkString, zup text )
        , "types")
    }

    def insertThemAll(nb: NodeBuffer, tableName:String) {
      nb.foreach {n =>
        val insertStatement =
          update(
              Array("value", "name")
            , Array(n.attribute("value").get.mkString, n text )
            , tableName)
    }
    def prepareBase() {
      val makeSchema = """
  DROP SCHEMA IF EXISTS institution CASCADE;
  CREATE SCHEMA institutions;
  """

      val makeZupTable = """
  Create Table institutions.County (
        value int
          CONSTRAINT pk_institutions_countyes PRIMARY KEY,
        name  text not null);
  """
      val makeInstitutionsTypeTable = """
  Create Table institutions.InstitutionType (
        value int
          CONSTRAINT pk_institutions_types PRIMARY KEY,
        name  text not null);
  """
      val makeDataTable = """
  Create Table institutions.Institution (
        cc    serial,
        name  text not null,
        addrese text,
        tel   text,
        county int
            references countyes(value),
        type  int
            references types(value)
        );
  """

    con.prepareStatement(makeSchema).execute()
    con.prepareStatement(makeZupTable).execute()
    con.prepareStatement(makeInstitutionsTypeTable).execute()
    con.prepareStatement(makeSchema).execute()

    }
  }

    val update = (
      header: Array[String]
    , values: Array[String]
    , tableName: String) =>
      {
        "insert into" + tableName +
            "(" +
            header.head +
            header.tail.map(", " + _).mkString +
            " values " +
            values.head +
            values.tail.map(", " + _).mkString +
          ");"
      }
  val tipovi = (
//  <option value="806">Ustanove socijalne skrbi</option>
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
