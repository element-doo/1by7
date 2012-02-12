package export.types;

import export.Type;

public class DataNull extends Data
{
  public DataNull()
  {
    super( Style.NULL, null );
  }

  @Override
  public Type getType()
  {
    return Type.DATA_NULL;
  }
}
