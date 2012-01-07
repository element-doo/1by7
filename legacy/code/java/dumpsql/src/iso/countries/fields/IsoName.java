package iso.countries.fields;

public class IsoName
{
	public static final String NAME = "iso_name";

	private final String isoName;
	
	public IsoName( final String isoName )
	{
		if ( !isoName.equals( isoName.toUpperCase() ) ) 
			throw new IllegalArgumentException( "ISO name must be uppercase! (" + isoName + ")" );
		
		this.isoName = isoName;	
	}

	public String getIsoName()
	{
		return isoName;
	}
}
