package iso.countries.dmp.fields;

import dmp.Config;
import dmp.Configurable;

public class Alpha2CodeName extends Configurable
{
	private final String alpha2CodeName;	
	
	public Alpha2CodeName( final Config config, final String alpha2CodeName )
	{
		super( config );
		this.alpha2CodeName = alpha2CodeName;
	}
	
	public String getAlpha2CodeName()
	{
		return alpha2CodeName;
	}
}
