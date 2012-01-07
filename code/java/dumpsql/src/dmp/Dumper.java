package dmp;

import dmp.sql.EntityQuoting;

public abstract class Dumper extends Configurable
{
	public Dumper( final Config config )
	{
		super( config );
	}
	
	public abstract String getNewLine();
	public abstract String getSQLKeywords( final String keywords );
	
	public abstract String escape( final String text );
	public abstract String getEntityQuote();
	
	public String getEntity( final String entity )
	{
		final boolean quote = config.entities.shouldQuote( entity );
		if ( !quote ) return escape( entity );
		
		final StringBuilder sB = new StringBuilder();		
		sB.append( getEntityQuote() );
		sB.append( escape( EntityQuoting.escape( entity ) ) );
		sB.append( getEntityQuote() );		
		return sB.toString();
	}	
	
	public static class Text extends Dumper
	{
		public Text( final Config config )
		{
			super( config );
		}
		
		public String getNewLine()
		{
			return config.newLine.getNewLine();
		}
		
		public String getSQLKeywords( final String keywords )
		{
			return keywords;
		}
		
		public String escape( final String text )
		{
			return text;
		}
		
		public String getEntityQuote()
		{
			return "\"";
		}
	}
	
	public static class Html extends Dumper
	{
		public Html( final Config config )
		{
			super( config );
		}
		
		public String getNewLine()
		{
			return "<span class=\"newline\">" + config.newLine.getHumanCodes() + "</span><br />"
				+ config.newLine.getNewLine();
		}

		public String getSQLKeywords( final String keywords )
		{
			return "<span class=\"keywords\">" + keywords + "</span>";
		}
		
		public String escape( final String text )
		{
			String work = text;
			
			work = work.replace( "&", "&amp;" ); 
			work = work.replace( "<", "&lt;" ); 
			work = work.replace( ">", "&gt;" ); 
			
			return work;
		}
		
		public String getEntityQuote()
		{
			return "&quote;";
		}
	}
}
