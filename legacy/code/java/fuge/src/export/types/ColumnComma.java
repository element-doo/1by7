package export.types;

import export.Type;

public class ColumnComma extends ColumnSeparator
{
  public ColumnComma()
  {
    super( Style.COMMA );
  }

  @Override
  public Type getType()
  {
    return Type.COLUMN_COMMA;
  }
}
