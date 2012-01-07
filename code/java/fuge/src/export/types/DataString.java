package export.types;

import export.Type;

public class DataString extends Data
{
	public DataString( final String data )
	{
		super( Style.STRING, data );
	}

	@Override
	public Type getType()
	{
		return Type.DATA_STRING;
	}

}
