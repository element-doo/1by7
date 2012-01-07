package export.types;

public abstract class RowSeparator extends Separator
{
	public enum Style { COMMA, SEMICOLON };
	
	public final Style style;
	
	public RowSeparator( final Style style )
	{
		super( Separator.Style.ROW );
		this.style = style;
	}
}
