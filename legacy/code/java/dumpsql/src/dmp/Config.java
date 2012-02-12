package dmp;

import iso.countries.IsoCountries;
import iso.countries.dmp.fields.Alpha2CodeName;
import iso.countries.dmp.fields.Alpha3CodeName;
import iso.countries.dmp.fields.IsoCountriesName;
import iso.countries.dmp.fields.IsoNameName;
import iso.countries.dmp.fields.Numeric3CodeName;
import iso.countries.fields.Alpha2Code;
import iso.countries.fields.Alpha3Code;
import iso.countries.fields.IsoName;
import iso.countries.fields.Numeric3Code;
import dmp.sql.EntityQuoting;

public class Config
{
  public final Dumper dumper;
  public final NewLine newLine;
  public final EntityQuoting entities;

  public final Alpha2CodeName alpha2CodeName;
  public final Alpha3CodeName alpha3CodeName;
  public final Numeric3CodeName numeric3CodeName;
  public final IsoNameName isoNameName;
  public final IsoCountriesName isoCountriesName;

  public Config()
  {
    dumper = new Dumper.Html( this );
    newLine = new NewLine.Windows( this );
    entities = new EntityQuoting.Always( this );

    alpha2CodeName = new Alpha2CodeName( this, Alpha2Code.NAME );
    alpha3CodeName = new Alpha3CodeName( this, Alpha3Code.NAME );
    numeric3CodeName = new Numeric3CodeName( this, Numeric3Code.NAME );
    isoNameName = new IsoNameName( this, IsoName.NAME );

    isoCountriesName = new IsoCountriesName( this, IsoCountries.NAME );
  }

  public String getNewLine()
  {
    return dumper.getNewLine();
  }

  public String getSQLKeyword( final String keywords )
  {
    return dumper.getSQLKeywords( keywords );
  }

  public String getEntity( final String entity )
  {
    return dumper.getEntity( entity );
  }
}
