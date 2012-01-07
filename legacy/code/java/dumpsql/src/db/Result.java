package db;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Result
{
	public final int rows, cols;
	private final Map<String,Integer> map;
	private final String[] vals;

	public Result( final ResultSet rS ) throws SQLException
	{
		try
		{
			final ResultSetMetaData rSMD = rS.getMetaData();
			cols = rSMD.getColumnCount();

			map = new LinkedHashMap<String, Integer>();

			for ( int i = 1; i <= cols; i ++ )
			{
				String col = rSMD.getColumnName( i );
				while ( map.containsKey( col ) ) col += '*';
				map.put( col, i );
			}
		}
		catch ( final SQLException e )
		{
			System.out.println( "Could not retrieve result set metadata:" );
			e.printStackTrace();
			throw e;
		}

		final List<String> ret = new ArrayList<String>();

		try
		{
			while( rS.next() )
			{
				for ( int r = 1; r <= cols; r ++ )
					ret.add( rS.getString( r ) );
			}
		}
		catch ( final SQLException e )
		{
			System.err.println( "Could not create Result!" );
			e.printStackTrace();
			throw e;
		}

		rows = ret.size() / cols;
		vals = ret.toArray( new String[ rows ] );
	}

	public String gS( final int row, final String column )
	{
		final Integer index = map.get( column );
		if ( index == null ) throw new IllegalArgumentException(
				"The Result doesn't contain column '" + column + "'" );

		return gS( row, index );
	}

	public Integer gI( final int row, final String column )
	{
		final String val = gS( row, column );
		if ( null == val ) return null;

		return Integer.parseInt( val );
	}

	public Long gL( final int row, final String column )
	{
		final String val = gS( row, column );
		if ( null == val ) return null;

		return Long.parseLong( val );
	}

	private String gS( final int row, final int col )
	{
		if ( ( col < 1 ) && ( col > cols ) ) throw new IllegalArgumentException(
				"Invalid column index! (" + col + " not in 1.." + cols + ")" );

		if ( ( row < 1 ) && ( row > rows ) ) throw new IllegalArgumentException(
				"Invalid row index! (" + row + " not in 1.." + rows + ")" );

		return vals[ ( row - 1 ) * cols + ( col - 1 ) ];
	}
}
