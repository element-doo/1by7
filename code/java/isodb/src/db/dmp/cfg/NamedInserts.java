package db.dmp.cfg;

import dmp.Config;

public class NamedInserts
{
	private final Config config;
	private final boolean named;
	
	public NamedInserts( final Config config, final boolean named )
	{
		this.config = config;
		this.named = named;
	}
	
	public boolean isNamed()
	{
		return named;
	}
}
