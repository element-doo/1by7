package export.types;

import export.Dumpable;

public abstract class Quote implements Dumpable
{
	public enum Style{ APOS, QUOT, BACKTICK };
	
	public final Style style;
	public final boolean opening;
	
	public Quote( final Style style, final boolean opening )
	{
		this.style = style;
		this.opening = opening;
	}
	
	public abstract String getOpen();
	public abstract String getClosed();
}
