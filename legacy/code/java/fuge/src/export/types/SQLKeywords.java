package export.types;

import export.Dumpable;
import export.Type;

public class SQLKeywords implements Dumpable
{
	public final String keywords;
	
	public SQLKeywords( final String keywords )
	{
		this.keywords = keywords;
	}
	
	@Override
	public Type getType()
	{
		return Type.SQL_KEYWORDS;
	}
}
