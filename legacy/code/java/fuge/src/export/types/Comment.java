package export.types;

import export.Dumpable;

public abstract class Comment implements Dumpable
{
  public enum Style{ LINE };

  public final Style style;

  public Comment( final Style style )
  {
    this.style = style;
  }
}
