package export.types;

import export.Dumpable;
import export.Type;

public class Entity implements Dumpable
{
	public final String entity;
	
	public Entity( final String entity )
	{
		this.entity = entity;
	}

	@Override
	public Type getType()
	{
		return Type.ENTITY;
	}
}
