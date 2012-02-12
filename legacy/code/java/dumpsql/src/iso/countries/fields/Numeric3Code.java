package iso.countries.fields;

import java.util.regex.Pattern;

public class Numeric3Code
{
  public static final String NAME = "numeric3_code";

  private final String numeric3Code;

  private static final Pattern NUMERIC3CODE_PATTERN = Pattern.compile( "\\d{3}" );

  public Numeric3Code( final String numeric3Code )
  {
    if ( !NUMERIC3CODE_PATTERN.matcher( numeric3Code ).matches() )
      throw new IllegalArgumentException( "Code is not three numbers! (" + numeric3Code + ")" );

    this.numeric3Code = numeric3Code;
  }

  public final String getNumeric3Code()
  {
    return numeric3Code;
  }
}
