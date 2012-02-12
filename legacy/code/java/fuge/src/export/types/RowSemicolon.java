package export.types;

import export.Type;

public class RowSemicolon extends RowSeparator
{
  public RowSemicolon()
  {
    super( Style.SEMICOLON );
  }

  @Override
  public Type getType()
  {
    return Type.ROW_SEMICOLON;
  }
}
