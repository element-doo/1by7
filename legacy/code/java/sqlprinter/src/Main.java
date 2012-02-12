public class Main
{
  public static void main( final String[] args )
  {
    final SQLPrinter sP = new SQLPrinter();

    sP.print( "section-open" );
    sP.print( "| + comment-open" );
    sP.print( "comment-line", "-- WARNING! This will erase the entire schema!" );
    sP.print( "nl" );
    sP.print( "comment-close" );
    sP.print( "| + command-open" );
    sP.print( "| + + keyword", "DROP" );
    sP.print( "| + + keyword", "SCHEMA" );
    sP.print( "| + + keyword", "IF" );
    sP.print( "| + + keyword", "EXISTS" );
    sP.print( "| + + entity-open" );
    sP.print( "entity-quote-open" );
    sP.print( "entity-schema", "iso" );
    sP.print( "entity-quote-close" );
    sP.print( "entity-close" );
    sP.print( "| + + keyword", "CASCADE" );
    sP.print( "command-separator" );
    sP.print( "nl" );
    sP.print( "| + command-close" );
    sP.print( "| + nl" );
    sP.print( "| section-close" );

/*    sP.print( "section-open" );
    sP.print( "  command-open" );
    sP.print( "    keyword-open", "CREATE SCHEMA " );
    sP.print( "      entity-open" );
    sP.print( "        entity-quote-open" );
    sP.print( "          entity-schema", "iso" );
    sP.print( "        entity-quote-close" );
    sP.print( "      entity-close" );
    sP.print( "      keyword", " CASCADE" );
    sP.print( "    keyword-close" );
    sP.print( "    command-separator" );
    sP.print( "  command-close" );
    sP.print( "  nl" );
    sP.print( "section-close" );
    sP.print( "nl" );
*/
//    System.out.println( sP.getText() );
    System.out.println( sP.getHtml() );
  }
}
/*
    sP.print( "nl" );

    sP.print( "keyword-sql-open", "CREATE SCHEMA " );
    sP.print( "quote-entity-open" );
    sP.print( "entity-schema", "iso" );
    sP.print( "quote-entity-close" );
    sP.print( "keyword-sql-close" );
    sP.print( "separator-command" );
    sP.print( "nl" );

    sP.print( "nl" );

    sP.print( "keyword-sql", "COMMENT ON SCHEMA " );
    sP.print( "quote-entity-open" );
    sP.print( "entity-table", "iso.countries" );
    sP.print( "quote-entity-close" );
    sP.print( "keyword-sql", " IS " );
    sP.print( "quote-data-open" );
    sP.print( "data-string", "ISO tables (read-only)" );
    sP.print( "quote-data-close" );
    sP.print( "separator-command" );
    sP.print( "nl" );

    sP.print( "nl" );

    sP.print( "keyword-sql", "CREATE TABLE " );
    sP.print( "quote-entity-open" );
    sP.print( "entity-table", "iso.countries" );
    sP.print( "quote-entity-close" );
    sP.print( "nl" );
    sP.print( "tab" );
    sP.print( "quote-entity-open" );
    sP.print( "entity-column", "alpha2_code" );
    sP.print( "quote-entity-close" );
    sP.print( "keyword-sql", " text" );
    sP.print( "nl" );
    sP.print( "tab" );
    sP.print( "tab" );
    sP.print( "keyword-sql", " text" );
    sP.print( "CONSTRAINT pk_iso_countries_alpha2_code PRIMARY KEY WITH(FILLFACTOR=100)
  }
}
*/
