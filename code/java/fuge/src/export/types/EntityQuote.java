package export.types;

import export.Type;

public class EntityQuote extends Quote
{
	public EntityQuote( final boolean opening )
	{
		super( Quote.Style.QUOT, opening );
	}
	
	@Override
	public Type getType()
	{
		return Type.ENTITY_QUOTE;
	}

	@Override
	public String getClosed()
	{
		return "\"";
	}

	@Override
	public String getOpen()
	{
		return "\"";
	}
}
