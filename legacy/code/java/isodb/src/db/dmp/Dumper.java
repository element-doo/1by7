package db.dmp;

import iso.countries.Row;
import iso.countries.Table;
import dmp.Config;

public class Dumper
{
  private final Table table;
  private final Config config;
  private final StrBld sB;

  public Dumper( final Table table, final Config config )
  {
    this.table = table;
    this.config = config;

    sB = new StrBld( config );
  }

  public void dumpInsertFields()
  {
    sB.wb( "(" ).
    a( config.alpha2CodeName ).wr( "," ).
    a( config.alpha3CodeName ).wr( "," ).
    a( config.numeric3CodeName ).wr( "," ).
    a( config.isoNameName ).wl( ")" );
  }

  public void dumpInsertHeader()
  {
    sB.a( "INSERT INTO " ).a( config.tableName );

    if ( config.namedInserts.isNamed() )
      dumpInsertFields();
  }

  public void dump()
  {
    dumpInsertHeader();
    sB.wl( "VALUES" ).n();

    final int count = table.rows.length;

    for ( int i = 0; i < count; i ++ )
    {
      final Row row = table.rows[ i ];

      sB.wr( "(" );



      sB.wl( ")" );

      sB.nr( i < count - 1 ? "," : ";" );
    }
  }

  public void echo()
  {
    dump();

    System.out.println( sB );
  }
}
