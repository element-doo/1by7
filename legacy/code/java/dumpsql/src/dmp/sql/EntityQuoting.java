package dmp.sql;

import java.util.regex.Pattern;

import dmp.Config;
import dmp.Configurable;
import dmp.Dumper;

public abstract class EntityQuoting extends Configurable
{
  private static final Pattern DOUBLE_QUOTE = Pattern.compile( "\"" );
  private static final Pattern SAFE_ENTITY = Pattern.compile( "\\w+" );

  private static boolean needsQuoting( final String entity )
  {
    return !SAFE_ENTITY.matcher( entity ).matches();
  }

  public static String escape( final String entity )
  {
    return DOUBLE_QUOTE.matcher( entity ).replaceAll( "\"\"" );
  }

  private EntityQuoting( final Config config )
  {
    super( config );
  }

  public abstract boolean shouldQuote( final String entity );

  public static class Always extends EntityQuoting
  {
    public Always( final Config config )
    {
      super( config );
    }

    public boolean shouldQuote( final String entity )
    {
      return true;
    }
  }

  public static class WhenNeeded extends EntityQuoting
  {
    public WhenNeeded( final Config config )
    {
      super( config );
    }

    public boolean shouldQuote( final String entity )
    {
      return needsQuoting( entity );
    }
  }
}
