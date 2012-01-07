package iso.countries;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;

import db.DB;
import db.Result;

public class IsoCountries implements Iterable<IsoCountriesRow>
{
	public static final String NAME = "iso.countries";

	private static final String getQuery()
	{
		return "SELECT alpha2_code,alpha3_code,numeric3_code,iso_name " +
			   "FROM iso.countries " +
			   "ORDER BY iso_name;";
	}
	
	private final Queue<IsoCountriesRow> rowQueue;
	
	public IsoCountries()
	{
		final DB db = new OneBySeven();
		final Result rS = db.query( getQuery() );
	
		rowQueue = new ArrayDeque<IsoCountriesRow>( rS.rows );

		for ( int i = 1; i <= rS.rows; i ++ )
		{
			final String alpha2Code = rS.gS( i, "alpha2_code" );
			final String alpha3Code = rS.gS( i, "alpha3_code" );
			final String numeric3Code = rS.gS( i, "numeric3_code" );
			final String isoName = rS.gS( i, "iso_name" );

			final IsoCountriesRow row = new IsoCountriesRow( alpha2Code, alpha3Code, numeric3Code, isoName );
			rowQueue.add( row );
		}
	}

	@Override
	public Iterator<IsoCountriesRow> iterator()
	{
		return rowQueue.iterator();
	}
}
