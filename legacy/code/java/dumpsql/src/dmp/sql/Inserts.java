package dmp.sql;

import dmp.Config;
import dmp.Configurable;

public abstract class Inserts extends Configurable
{
	private Inserts( final Config config )
	{
		super( config );
	}
	
	public static class Single extends Inserts
	{
		public Single( final Config config )
		{
			super( config );
		}		
	}	
	
	public static class Multiple extends Inserts
	{
		public Multiple( final Config config )
		{
			super( config );
		}		
	}	
	
	public static class MultipleNamed extends Inserts
	{
		public MultipleNamed( final Config config )
		{
			super( config );
		}		
	}	
}
