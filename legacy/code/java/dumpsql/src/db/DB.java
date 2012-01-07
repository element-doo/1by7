package db;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class DB
{
	private final String host, db, user, pass;

	public DB( final String host, final String db, final String user, final String pass )
	{
		this.host = host;
		this.db = db;
		this.user = user;
		this.pass = pass;
	}

	private static Connection openConnection( final String host, final String db, final String user, final String pass )
	{
		try
		{
			Class.forName( "org.postgresql.Driver" );
		}
		catch ( final ClassNotFoundException e )
		{
			System.err.println( "Couldn't find driver class!" );
			e.printStackTrace();
			return null;
		}

		final String target = host + '/' + db;

		try
		{
			return DriverManager
				.getConnection( "jdbc:postgresql://" + target, user, pass );
		}
		catch ( final SQLException e )
		{
		    System.out.println( "Couldn't connect to " + target + '!' );
		    e.printStackTrace();
		    return null;
		}
	}

	private Connection conn;

	private boolean ensureConnection()
	{
		if ( conn == null )
			conn = openConnection( host, db, user, pass );

		return conn != null;
	}

	private Statement createStatement()
	{
		if ( !ensureConnection() ) return null;

		try
		{
			return conn.createStatement();
		}
		catch ( final SQLException e )
		{
			System.err.println( "Could not create statement:" );
			e.printStackTrace();
			return null;
		}
	}

	public int exec( final String query )
	{
		try
		{
			return createStatement().executeUpdate( query );
		}
		catch ( final SQLException e )
		{
			System.err.println( "Could not execute the query:" );
			e.printStackTrace();
			return -1;
		}
	}

	public Result query( final String query )
	{
		try
		{
			return new Result( createStatement().executeQuery( query ) );
		}
		catch ( final SQLException e )
		{
			System.err.println( "Could not query the query:" );
			e.printStackTrace();
			return null;
		}
	}
	
	public int execFile( final String path )
	{
		String query = null;
		
		try
		{
			final File file = new File( path );
			final FileInputStream fIS = new FileInputStream( file );
			final byte[] buf = new byte[ (int) file.length() ];
			fIS.read( buf );
			fIS.close();
			
			query = new String( buf, "UTF-8" );
			return exec( query );
		}
		catch( final IOException e )
		{
			System.err.println( "Could not read file: " + path );
			e.printStackTrace();
			return -2;
		}
	}
}
