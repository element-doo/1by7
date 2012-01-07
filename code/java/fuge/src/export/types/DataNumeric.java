package export.types;

import export.Type;

public class DataNumeric extends Data
{
	public DataNumeric( final String data )
	{
		super( Style.NUMERIC, data );
	}

	@Override
	public Type getType()
	{
		return Type.DATA_NUMERIC;
	}
}
