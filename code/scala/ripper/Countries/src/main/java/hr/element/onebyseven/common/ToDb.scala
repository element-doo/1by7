//package hr.element.onebyseven.common
//import hr.element.onebyseven.common.old.Country;
//
//import java.sql.DriverManager
//import java.util.Properties
//
//case class ConnectionConfiguration(
//    driver: String,
//    dbProv: String,
//    dbName: String,
//    host: String,
//    port: String,
//    user: String,
//    pass: String)
//class ToDb(cc: ConnectionConfiguration) {
//  Class.forName(cc.driver)
//  val cp = new Properties();
//  cp.put("user", cc.user);
//  cp.put("password", cc.pass);
//  val con = DriverManager.getConnection("jdbc:" + cc.dbProv + ":" + cc.dbName+"://" +
//            cc.host +
//            ":" + cc.port + "/", cp)
//  val makeTableStatement = " Create table countries (" +
//                            "alpha2 text primary key," +
//                            "alpha3 text not null," +
//                            "numeric3 integer," +
//                            "wikiname text" +
//                            "); "
//  val addCountryStatement = "insert into countries " +
//      "(alpha2, alpha3, numeric3, wikiname ) values" +
//      " (?, ?, ?, ?);"
//
//  def addTable(){ //}name: String, a: Map[String]){
//    val a = con.prepareStatement(makeTableStatement)
//    a.execute()
//  }
//
//  def addCountires(){
//    val insertStatement = con.prepareStatement(addCountryStatement)
//    Country.values.foreach{ x =>
//      insertStatement.setString(0, x.alpha2)
//      insertStatement.setString(1, x.alpha3)
//      insertStatement.setInt(   2, Integer.parseInt(x.numeric3))
//      insertStatement.setString(3, x.wikiName)
//      insertStatement.executeUpdate()
//      }
//    insertStatement.close()
//  }
//}