package db.dmp.cfg;

import dmp.Config;

public class Whitespaces
{
	private final Config config;
	private final boolean separate;
	
	public Whitespaces( final Config config, final boolean separate )
	{
		this.config = config;
		this.separate = separate;
	}
	
	public boolean separate()
	{
		return separate;
	}
}
