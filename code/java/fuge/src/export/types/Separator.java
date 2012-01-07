package export.types;

import export.Dumpable;

public abstract class Separator implements Dumpable
{
	public enum Style { COLUMN, ROW };
	
	public final Style style;
	
	public Separator( final Style style )
	{
		this.style = style;
	}
}
