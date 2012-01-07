package export.types;

import export.Type;

public class DataQuote extends Quote
{
	public DataQuote( final Style style, final boolean opening )
	{
		super( style, opening );
	}
	
	@Override
	public Type getType()
	{
		return Type.DATA_QUOTE;
	}
		
	public String getOpen()
	{
		return "'";
	}
	
	public String getClosed()
	{
		return "'";
	}
}
