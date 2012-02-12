package export.types;

import export.Type;

public class DashComment extends LineComment
{
  public final String comment;

  public DashComment( final String comment )
  {
    super( LineComment.Style.DASH );
    this.comment = comment;
  }

  @Override
  public Type getType()
  {
    return Type.DASH_COMMENT;
  }
}
