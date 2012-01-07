package iso.countries;

import iso.countries.fields.Alpha2Code;
import iso.countries.fields.Alpha3Code;
import iso.countries.fields.IsoName;
import iso.countries.fields.Numeric3Code;

public class IsoCountriesRow
{
	public Alpha2Code alpha2Code;
	public Alpha3Code alpha3Code;	
	public Numeric3Code numeric3Code;
	public IsoName isoName;
	
	public IsoCountriesRow( final String alpha2Code, final String alpha3Code, final String numeric3Code, final String isoName )
	{
		this.alpha2Code = new Alpha2Code( alpha2Code );
		this.alpha3Code = new Alpha3Code( alpha3Code );
		this.numeric3Code = new Numeric3Code( numeric3Code );
		this.isoName = new IsoName( isoName );
	}
}
