package db.dmp.cfg;

import iso.countries.Table;
import iso.countries.dmp.fields.Alpha2CodeName;
import iso.countries.dmp.fields.Alpha3CodeName;
import iso.countries.dmp.fields.IsoNameName;
import iso.countries.dmp.fields.Numeric3CodeName;
import iso.countries.dmp.fields.TableName;
import iso.countries.fields.Alpha2Code;
import iso.countries.fields.Alpha3Code;
import iso.countries.fields.IsoName;
import iso.countries.fields.Numeric3Code;
import db.dmp.cfg.Encoding;
import db.dmp.cfg.FormattedInserts;
import db.dmp.cfg.NamedInserts;
import db.dmp.cfg.Whitespaces;
import dmp.NewLine;
import dmp.sql.EntitiesQuoting;
import dmp.sql.EntitiesQuoting.Always;

public class Config
{
  public final NewLine newLine;
  public final Encoding encoding;
  public final EntitiesQuoting entitiesQuoting;

  public final NamedInserts namedInserts;
  public final FormattedInserts formattedInserts;
  public final Whitespaces whitespaces;

  public final TableName tableName;
  public final Alpha2CodeName alpha2CodeName;
  public final Alpha3CodeName alpha3CodeName;
  public final Numeric3CodeName numeric3CodeName;
  public final IsoNameName isoNameName;

  public Config()
  {
    newLine = new NewLine( this, NewLine.WINDOWS );
    encoding = new Encoding( this, Encoding.UTF8 );
    entitiesQuoting = new EntitiesQuoting.Always( this );

    namedInserts = new NamedInserts( this, true );
    formattedInserts = new FormattedInserts( this, true );
    whitespaces = new Whitespaces( this, true );

    tableName = new TableName( this, Table.NAME );
    alpha2CodeName = new Alpha2CodeName( this, Alpha2Code.NAME );
    alpha3CodeName = new Alpha3CodeName( this, Alpha3Code.NAME );
    numeric3CodeName = new Numeric3CodeName( this, Numeric3Code.NAME );
    isoNameName = new IsoNameName( this, IsoName.NAME );
  }
}
