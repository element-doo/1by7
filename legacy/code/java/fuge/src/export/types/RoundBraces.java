package export.types;

import export.Type;

public class RoundBraces extends Braces
{
  public RoundBraces( final boolean opening )
  {
    super( Style.ROUND, opening );
  }

  @Override
  public Type getType()
  {
    return Type.BRACES_ROUND;
  }

  @Override
  public String getOpen()
  {
    return "(";
  }

  @Override
  public String getClosed()
  {
    return ")";
  }
}
