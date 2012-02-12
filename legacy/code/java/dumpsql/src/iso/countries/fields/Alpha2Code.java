package iso.countries.fields;

import java.util.regex.Pattern;

public class Alpha2Code
{
  public static final String NAME = "alpha2_code";

  private final String alpha2Code;

  private static final Pattern ALPHA2CODE_PATTERN = Pattern.compile( "[A-Z]{2}" );

  public Alpha2Code( final String alpha2Code )
  {
    if ( !ALPHA2CODE_PATTERN.matcher( alpha2Code ).matches() )
      throw new IllegalArgumentException( "Code is not two letters! (" + alpha2Code + ")" );

    this.alpha2Code = alpha2Code;
  }

  public String getAlpha2Code()
  {
    return alpha2Code;
  }
}
