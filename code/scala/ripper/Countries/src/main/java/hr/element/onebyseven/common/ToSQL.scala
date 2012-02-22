package hr.element.onebyseven.common
import java.io.FileOutputStream
import java.io.OutputStreamWriter
import java.io.Writer

sealed abstract class bases
case object MysqlOption extends bases
case object PgsqlOption extends bases
case object MSsqlOption

object ToSQL {
  def apply(w: Writer, opt: bases) {
    opt match {
      case PgsqlOption => new PgSqlWrite(w)
      case MysqlOption => new MySqlWrite(w)
    }
  }
}

abstract class SqlCountriesInsertWriter(w: Writer) {
  val header: String
  val closingStatement: String
  def spaces(i: Int): String =
    if (i == 0) " "
    else spaces(i - 1) + " "
  val cValues = Country.values
  val maxWikiSpacing = cValues.maxBy(_.wikiName.length).wikiName.length
  val insertHeader =
    "INSERT INTO iso.countries(alpha2_code,alpha3_code,numeric3_code,iso_name) VALUES\n"
  val createInsert = (c: Country) =>
    "('%s', '%s', '%s', '%s' %s)".format(
      c.alpha2,
      c.alpha3,
      c.numeric3,
      c.isoName,
      spaces(maxWikiSpacing - c.isoName.length()))
  def write() ={
        w.write(header)
  w.write(insertHeader)
  w.write(createInsert(cValues.head))
  for (c <- cValues.tail) {
    w.write(",\n")
    w.write(createInsert(c))
  }
  w.write(";\n\n")

  w.write(closingStatement)
  w.close()
    }
}
class MySqlWrite(w: Writer) extends SqlCountriesInsertWriter(w) {

  val header =
    """DROP DATABASE IF EXISTS iso;
CREATE DATABASE IF NOT EXISTS iso;
USE iso

DROP TABLE IF EXISTS countries

SELECT 'CREATING DATABASE STRUCTURE' as 'INFO';

SET sql_mode = 'STRICT_ALL_TABLES';

CREATE TABLE iso.countries
(
  alpha2_code   VARCHAR(2)    NOT NULL
    COMMENT 'PK: Two-letter country code',

  alpha3_code   VARCHAR(3)    NOT NULL
    COMMENT 'UQ: Three-letter country code',

  numeric3_code VARCHAR(3)    NOT NULL
    COMMENT 'UQ: Three-digit country code',

  iso_name      TINYBLOB      NOT NULL
    COMMENT 'UQ: ISO country name',

  PRIMARY KEY (alpha2_code)
) COMMENT
 'ISO 3166-1: Codes for countries, dependent territories, and special areas of geographical interest';
"""


  val closingStatement = ""

  w.write(header)
  w.write(insertHeader)
  w.write(createInsert(cValues.head))
  for (c <- cValues.tail) {
    w.write(",\n")
    w.write(createInsert(c))
  }
  w.write(";\n\n")

  //w.write(clusterStatement)
  w.close()

}

class PgSqlWrite(w: Writer) extends SqlCountriesInsertWriter(w) {

  /*val osf = new FileOutputStream(fileName)
    val w = new OutputStreamWriter(osf)*/

  val header = """DROP SCHEMA IF EXISTS iso CASCADE;

CREATE SCHEMA iso;
COMMENT ON SCHEMA iso IS 'ISO tables (read-only)';

CREATE TABLE iso.countries
(
  alpha2_code text NOT NULL
    CONSTRAINT pk_iso_countries_alpha2_code PRIMARY KEY WITH(FILLFACTOR=100)
    CONSTRAINT ck_iso_countries_alpha2_code CHECK(alpha2_code~'^[A-Z]{2}$'),
  alpha3_code text
    CONSTRAINT uq_iso_countries_alpha3_code UNIQUE WITH(FILLFACTOR=100)
    CONSTRAINT ck_iso_countries_alpha3_code CHECK(alpha3_code~'^[A-Z]{3}$'),
  numeric3_code text
    CONSTRAINT uq_iso_countries_numeric3_code UNIQUE WITH(FILLFACTOR=100)
    CONSTRAINT ck_iso_countries_numeric3_code CHECK(numeric3_code~'^\d{3}$'),
  iso_name text NOT NULL
    CONSTRAINT uq_iso_countries_iso_name UNIQUE WITH(FILLFACTOR=100)
)
WITH(FILLFACTOR=100);

COMMENT ON TABLE iso.countries IS 'ISO 3166-1: Codes for countries, dependent territories, and special areas of geographical interest';
COMMENT ON COLUMN iso.countries.alpha2_code IS 'PK: Two-letter country code';
COMMENT ON COLUMN iso.countries.alpha3_code IS 'UQ: Three-letter country code';
COMMENT ON COLUMN iso.countries.numeric3_code IS 'UQ: Three-digit country code';
COMMENT ON COLUMN iso.countries.iso_name IS 'UQ: ISO country name';

ALTER TABLE iso.countries CLUSTER ON uq_iso_countries_iso_name;

"""

  val closingStatement = "CLUSTER iso.countries;"

}

class MicrosoftSqlWriter(w: Writer) extends SqlCountriesInsertWriter(w) {
  val header = """
SCHEMA AUTHORIZATION iso;

CREATE TABLE countries
(
  alpha2_code text NOT NULL
    PRIMARY KEY
    WITH FILLFACTOR = 100
    CHECK(alpha2_code ~ '^[A-Z]{2}$'),
  alpha3_code text
      UNIQUE
      WITH FILLFACTOR = 100
      CHECK(alpha3_code~'^[A-Z]{3}$'),
  numeric3_code text
      UNIQUE
      CHECK(numeric3_code~'^\d{3}$'),
  iso_name text NOT NULL
      UNIQUE
      WITH FILLFACTOR = 100
)
go
WITH FILLFACTOR = 100;
declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description',
   'ISO 3166-1: Codes for countries, dependent territories, and special areas of geographical interest',
   'user', @CurrentUser, 'table', 'TABLE_1'
go
declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description',
   'PK: Two-letter country code',
   'user', @CurrentUser, 'table', 'TABLE_1', 'column', 'alpha2_code'
go
declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description',
   'UQ: Three-letter country code',
   'user', @CurrentUser, 'table', 'TABLE_1', 'column', 'alpha3_code'
go
declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description',
   'UQ: Three-digit country code',
   'user', @CurrentUser, 'table', 'TABLE_1', 'column', 'numeric3_code'
go
declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description',
   'UQ: ISO country name',
   'user', @CurrentUser, 'table', 'TABLE_1', 'column', 'iso_name'
go


"""

  val closingStatement = "CLUSTER iso.countries;"


}
