package iso.countries.fields;

import java.util.regex.Pattern;

public class Alpha3Code
{
  public static final String NAME = "alpha3_code";

  private final String alpha3Code;

  private static final Pattern ALPHA3CODE_PATTERN = Pattern.compile( "[A-Z]{3}" );

  public Alpha3Code( final String alpha3Code )
  {
    if ( !ALPHA3CODE_PATTERN.matcher( alpha3Code ).matches() )
      throw new IllegalArgumentException( "Code is not three letters! (" + alpha3Code + ")" );

    this.alpha3Code = alpha3Code;
  }

  public String getAlpha3Code()
  {
    return alpha3Code;
  }
}
