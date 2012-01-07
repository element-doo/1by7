package db.dmp.cfg;

import dmp.Config;

public abstract class OutputFormatter
{
	private final Config config;
	
	public OutputFormatter( final Config config )
	{
		this.config = config;
	}
	
	public abstract String getData( final String data );
	
	public static class Text extends OutputFormatter
	{
		public Text( final Config config )
		{
			super( config );
		}
		
		public String getData( final String data )
		{
			return data;
		}
	}	
	
	public static class Html extends OutputFormatter
	{
		public Html( final Config config )
		{
			super( config );
		}
		
		public String getData( final String data )
		{
			return "<span class=\"data\">" + data + "</span>";
		}
	}	
}
