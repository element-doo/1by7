package export.types;

import export.Dumpable;

public abstract class Braces implements Dumpable
{
	public enum Style { ROUND };
	
	public final Style style;
	public final boolean opening;
	
	public Braces( final Style style, final boolean opening )
	{
		this.style = style;
		this.opening = opening;
	}
	
	public abstract String getOpen();
	public abstract String getClosed();
}
