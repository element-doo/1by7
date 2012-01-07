package export.types;

public abstract class ColumnSeparator extends Separator
{
	public enum Style { COMMA };
	
	public final Style style;
	
	public ColumnSeparator( final Style style )
	{
		super( Separator.Style.COLUMN );
		this.style = style;
	}
}
