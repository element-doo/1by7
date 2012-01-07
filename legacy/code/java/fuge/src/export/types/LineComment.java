package export.types;

public abstract class LineComment extends Comment
{
	public enum Style { DASH };
	
	public final Style style;
	
	public LineComment( final Style style )
	{
		super( Comment.Style.LINE );
		this.style = style;
	}
}
