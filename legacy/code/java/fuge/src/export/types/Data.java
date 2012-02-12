package export.types;

import export.Dumpable;

public abstract class Data implements Dumpable
{
  public enum Style { STRING, NUMERIC, NULL };

  public final Style style;
  public final String data;

  public Data( final Style style, final String data )
  {
    this.style = style;
    this.data = data;
  }
}
