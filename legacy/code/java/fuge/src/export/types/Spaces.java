package export.types;

import export.Dumpable;
import export.Type;

public class Spaces implements Dumpable
{
  public final int count;

  public Spaces( final int count )
  {
    this.count = count;
  }

  @Override
  public Type getType()
  {
    return Type.SPACES;
  }
}
