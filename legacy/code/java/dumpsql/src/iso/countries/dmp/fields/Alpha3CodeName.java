package iso.countries.dmp.fields;

import dmp.Config;
import dmp.Configurable;

public class Alpha3CodeName extends Configurable
{
	private final String alpha3CodeName;	
	
	public Alpha3CodeName( final Config config, final String alpha3CodeName )
	{
		super( config );
		this.alpha3CodeName = alpha3CodeName;
	}
	
	public String getAlpha3CodeName()
	{
		return alpha3CodeName;
	}
}
