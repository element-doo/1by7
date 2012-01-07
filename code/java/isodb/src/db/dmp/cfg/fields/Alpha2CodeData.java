package db.dmp.cfg.fields;

import iso.countries.Row;
import dmp.Config;

public abstract class Alpha2CodeData
{
	private final Config config;
	
	public Alpha2CodeData( final Config config )
	{
		this.config = config;
	}
	
	public String getFormatted( final Row row )
	{
		return config.o getData( row );
	}
	
	public abstract String getData( final Row row );
	
	public static class Uppercase extends Alpha2CodeData
	{
		public Uppercase( final Config config )
		{
			super( config );
		}
		
		public String getData( final Row row )
		{
			return row.a2.toString().toUpperCase();
		}
	}
}
