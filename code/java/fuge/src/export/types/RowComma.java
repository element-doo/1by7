package export.types;

import export.Type;

public class RowComma extends RowSeparator
{
	public RowComma()
	{
		super( Style.COMMA );
	}

	@Override
	public Type getType()
	{
		return Type.ROW_COMMA;
	}
}
