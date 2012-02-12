import java.util.ArrayDeque;
import java.util.Queue;
import java.util.regex.Pattern;

public class SQLPrinter
{
  private final Queue<String[]> parts;

  public SQLPrinter()
  {
    parts = new ArrayDeque<String[]>();
  }

  public void print( final String... sA )
  {
    parts.add( sA );
  }

  public String getText()
  {
    final StringBuilder sB = new StringBuilder();

    for ( final String[] sA : parts )
    {
      String type = sA[ 0 ].toLowerCase();
      final String data = sA.length > 1 ? sA[ 1 ] : null;

      if ( type.equals( "nl" ) )
      {
        sB.append( "\r\n" );
      }

      else if ( type.equals( "tab" ) )
      {
        sB.append( "    " );
      }

      else if ( type.equals( "command-open" ) ) {}
      else if ( type.equals( "command-close" ) ) {}
      else if ( type.equals( "command-separator" ) )
      {
        sB.append( ";" );
      }

      else if ( type.equals( "keyword-open" ) )
      {
        sB.append( data );
      }
      else if ( type.equals( "keyword" ) )
      {
        sB.append( data );
      }
      else if ( type.equals( "keyword-close" ) ) {}

      else if ( type.equals( "entity-open" ) ) {}
      else if ( type.equals( "entity-close" ) ) {}

      else if ( type.equals( "entity-quote-open" ) )
      {
        sB.append( "\"" );
      }
      else if ( type.equals( "entity-quote-close" ) )
      {
        sB.append( "\"" );
      }

      else if ( type.equals( "entity-schema" ) )
      {
        sB.append( data );
      }
      else if ( type.equals( "section-open" ) ) {}
      else if ( type.equals( "section-close" ) ) {}

      else if ( type.equals( "comment-line" ) )
      {
        sB.append( "-- " + data );
      }

      else throw new RuntimeException( "Unknown type: " + type );
    }

    return sB.toString();
  }

  public String h( final String text )
  {
    final StringBuilder sB = new StringBuilder();

    for( final char c : text.toCharArray() )
    {
      if ( c == '&' )
      {
        sB.append( "&amp;" );
      }
      else if ( c == '<' )
      {
        sB.append( "&lt;" );
      }
      else if ( c == '>' )
      {
        sB.append( "&gt;" );
      }
      else if ( c == '"' )
      {
        sB.append( "&quot;" );
      }
      else
      {
        sB.append( c );
      }
    }

    return sB.toString();
  }

  public String getHtml()
  {
    final StringBuilder sB = new StringBuilder();

    for ( final String[] sA : parts )
    {
      String type = sA[ 0 ].toLowerCase();

      while ( true )
      {
        boolean unchanged = true;

        while ( type.startsWith( "| " ) )
        {
          type = type.substring( 2 );
          unchanged = false;
          sB.append( "\r\n" );
        }

        while ( type.startsWith( "+ " ) )
        {
          type = type.substring( 2 );
          unchanged = false;
          sB.append( "\t" );
        }

        if ( unchanged ) break;
      }

      final String data = sA.length > 1 ? sA[ 1 ] : null;

      if ( type.isEmpty() )
      {
        sB.append( h( data ) + "" );
      }

      else if ( type.equals( "nl" ) )
      {
        sB.append( "<!-- NEWLINE OPEN --><span class=\"newline\">" + h( "[CR][LF]" )
          + "</span><br /><!-- NEWLINE CLOSE -->" );
      }
      else if ( type.equals( "space" ) )
      {
        sB.append( "<!-- SPACE OPEN --><span class=\"space\">&nbsp;</span><!-- SPACE CLOSE -->" );
      }

      else if ( type.equals( "command-open" ) )
      {
        sB.append( "<!-- COMMAND OPEN --><span class=\"command\">" );
      }
      else if ( type.equals( "command-close" ) )
      {
        sB.append( "</span><!-- COMMAND CLOSE -->" );
      }
      else if ( type.equals( "command-separator" ) )
      {
        sB.append( "<!-- COMMAND SEPARATOR OPEN -->"
          + "<span class=\"separator\">" + h( ";" )
          + "</span><!-- COMMAND SEPARATOR CLOSE -->" );
      }

      else if ( type.equals( "keyword" ) )
      {
        sB.append( "<!-- KEYWORD OPEN --><span class=\"keyword\">" + h( data ) + "</span><!-- KEYWORD CLOSE -->" );
      }

      else if ( type.equals( "entity-open" ) )
      {
        sB.append( "<!-- ENTITY OPEN --><span class=\"entity\">" );
      }
      else if ( type.equals( "entity-close" ) )
      {
        sB.append( "</span><!-- ENTITY CLOSE -->" );
      }

      else if ( type.equals( "entity-quote-open" ) )
      {
        sB.append( "<!-- ENTITY QUOTE OPEN OPEN --><span class=\"entity-quote\">" + h( "\"" ) + "</span><!-- ENTITY QUOTE OPEN CLOSE -->" );
      }
      else if ( type.equals( "entity-quote-close" ) )
      {
        sB.append( "<!-- ENTITY QUOTE CLOSE OPEN --><span class=\"entity-quote\">" + h( "\"" ) + "</span><!-- ENTITY QUOTE CLOSE CLOSE -->" );
      }

      else if ( type.equals( "entity-schema" ) )
      {
        sB.append( "<!-- ENTITY SCHEMA OPEN --><span class=\"entity-schema\">" + h( data ) + "</span><!-- ENTITY SCHEMA CLOSE -->" );
      }
      else if ( type.equals( "section-open" ) )
      {
        sB.append( "<!-- SECTION OPEN --><div class=\"section\">" );
      }
      else if ( type.equals( "section-close" ) )
      {
        sB.append( "</div><!-- SECTION CLOSE -->" );
      }

      else if ( type.equals( "comment-open" ) )
      {
        sB.append( "<!-- COMMENT OPEN --><span class=\"comment\">" );
      }
      else if ( type.equals( "comment-close" ) )
      {
        sB.append( "</span><!-- COMMENT CLOSE -->" );
      }
      else if ( type.equals( "comment-line" ) )
      {
        sB.append( "<!-- COMMENT LINE OPEN --><span class=\"comment-line\">"
          + h( data ) + "</span><!-- COMMENT LINE CLOSE -->" );
      }

      else throw new RuntimeException( "Unknown type: " + type );
    }

    return sB.toString();
  }
}
