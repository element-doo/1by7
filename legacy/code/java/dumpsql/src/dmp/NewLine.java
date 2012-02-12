package dmp;

public abstract class NewLine extends Configurable
{
  private final String newLine;
  private final String humanCodes;

  private NewLine( final Config config, final String newLine )
  {
    super( config );
    this.newLine = newLine;
    this.humanCodes = humanize( newLine );
  }

  private static String humanize( final String newLine )
  {
    final StringBuilder sB = new StringBuilder();

    for ( final char c : newLine.toCharArray() )
    {
      if ( '\r' == c ) sB.append( "[CR]" ); else
      if ( '\n' == c ) sB.append( "[LF]" );
    }

    return sB.toString();
  }

//  -------------------------------------------------------------------

  public String getNewLine()
  {
    return newLine;
  }

  public String getHumanCodes()
  {
    return humanCodes;
  }

//  -------------------------------------------------------------------

  public static class Windows extends NewLine
  {
    public Windows( final Config config )
    {
      super( config, "\r\n" );
    }
  }

  public static class Unix extends NewLine
  {
    public Unix( final Config config )
    {
      super( config, "\n" );
    }
  }

  public static class MacOS9 extends NewLine
  {
    public MacOS9( final Config config )
    {
      super( config, "\r" );
    }
  }
}
