package export.types;

import export.Dumpable;
import export.Type;

public class Newline implements Dumpable
{
  @Override
  public Type getType()
  {
    return Type.NEWLINE;
  }
}
