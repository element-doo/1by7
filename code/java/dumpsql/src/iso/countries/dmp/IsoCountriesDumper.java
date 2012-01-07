package iso.countries.dmp;

import iso.countries.IsoCountries;
import dmp.Config;
import dmp.Configurable;

public class IsoCountriesDumper extends Configurable
{
	private final IsoCountries isoCountries;
	
	public IsoCountriesDumper( final Config config, final IsoCountries isoCountries )
	{
		super( config );
		this.isoCountries = isoCountries;
	}
	
	public String toString()
	{
		final StringBuilder sB = new StringBuilder();
		
		sB.append( config.getSQLKeyword( "INSERT INTO" ) ).append( " " );
		
		sB.append( config.getEntity( config.isoCountriesName.getIsoCountriesName() ) ); 
		
		return sB.toString();
	}
}
