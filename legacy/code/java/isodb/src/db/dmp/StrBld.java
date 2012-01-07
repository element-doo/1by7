package db.dmp;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dmp.Config;

public class StrBld implements Appendable, Cloneable, Iterable<String>, Serializable
{
//  ################################################################################

	private final Config config;
	
	private final ArrayDeque<String> sQ;
	private final StringBuilder sB;

	public StrBld( final Config config )
	{
		this.config = config;
		
		sB = new StringBuilder();
		sQ = new ArrayDeque<String>();
	}
	
//  ================================================================================

	public StrBld clear()
	{
		sB.setLength( 0 );
		sQ.clear();
		return this;
	}

//  ================================================================================

	private Pattern PNL = Pattern.compile( "\r\n|[\r\n]" );

	public StrBld a( final String s )
	{
		final Matcher m = PNL.matcher( s );

		boolean result = m.find();

		if ( !result )
		{
			sB.append( s );
			return this;
		}

		int last = 0;

		do
		{
			sQ.add( s.substring( last, m.start() ) );
			last = m.end();
			result = m.find();
		}
		while ( result );

		sB.append( s.substring( last ) );

		return this;
	}

	public StrBld a( final Dumpable dmp )
	{
		return a( dmp.toString() );
	}
	
//  --------------------------------------------------------------------------------

	public Appendable append( final CharSequence cS )
	{
		return a( cS.toString() );
	}

	public Appendable append( char c )
	{
		return c( c );
	}

	public Appendable append( CharSequence cS, int start, int end )
	{
		return append( cS.subSequence( start, end ) );
	}

//  --------------------------------------------------------------------------------

	public StrBld ah( final StrBld strBld )
	{
		final String jS = strBld.sB.toString();

		if ( !sQ.isEmpty() )
		{
			sQ.addFirst( jS + sQ.removeFirst() );
		}
		else sB.insert( 0, jS );

		final Iterator<String> sI = strBld.sQ.descendingIterator();
		while ( sI.hasNext() ) sQ.addFirst( sI.next() );

		return this;
	}

//  --------------------------------------------------------------------------------

	public StrBld at( final StrBld strBld )
	{
		if ( !strBld.sQ.isEmpty() )
		{
			final Iterator<String> sI = strBld.sQ.iterator();

			sB.append( sI.next() );
			n();

			while( sI.hasNext() ) sQ.add( sI.next() );
		}

		sB.append( strBld.sB.toString() );

		return this;
	}

//  ================================================================================

	public StrBld c( final char c )
	{
		if ( ( c == '\r' ) || ( c == '\n' ) ) return n();

		sB.append( c );
		return this;
	}

	public StrBld c( final char c, final int count )
	{
		for ( int i = 0; i < count; i ++ ) c( c );
		return this;
	}

//  ================================================================================

	public StrBld s()
	{
		return c( ' ' );
	}

	public StrBld s( final int count )
	{
		for ( int i = 0; i < count; i ++ ) s();
		return this;
	}

	public StrBld sl( final String s )
	{
		return s().a( s );
	}

	public StrBld sr( final String s )
	{
		return a( s ).s();
	}

	public StrBld sb( final String s )
	{
		return s().a( s ).s();
	}

//  ================================================================================

	public StrBld w()
	{
		if ( !config.whitespaces.separate() ) return this;
		
		return s();
	}

	public StrBld w( final int count )
	{
		for ( int i = 0; i < count; i ++ ) w();

		return this;
	}

	public StrBld wl( final String s )
	{
		return w().a( s );
	}

	public StrBld wr( final String s )
	{
		return a( s ).w();
	}

	public StrBld wb( final String s )
	{
		return w().a( s ).w();
	}

//  ================================================================================

	public StrBld n()
	{
		sQ.add( sB.toString() );
		sB.setLength( 0 );
		return this;
	}

	public StrBld nl( final String s )
	{
		return n().a( s );
	}

	public StrBld nr( final String s )
	{
		return a( s ).n();
	}

	public StrBld nb( final String s )
	{
		return n().a( s ).n();
	}

//  ================================================================================

	public StrBld a( final int i )
	{
		sB.append( i );
		return this;
	}
	
//  --------------------------------------------------------------------------------

	public StrBld fs( final String s, final int spaces )
	{
		return fA( "%" + spaces + "s", s );
	}

	public StrBld fz( final int i, final int digits )
	{
		return fA( "%0" + digits + "d", i );
	}

	public StrBld fx( final int i, final int digits )
	{
		return fA( "%0" + digits + "X", i );
	}

	public StrBld ff( final float f, final int decimals )
	{
		return fA( "%." + decimals + "f", f );
	}

	public StrBld fA( final String format, final Object... oA )
	{
		new Formatter( this ).format( format, oA ).flush();
		return this;
	}

//  ================================================================================

	public StrBld trim()
	{
		final StrBld nB = new StrBld( config );

		for ( final String cS : sQ )
		{
			final String trim = cS.trim();
			if ( !trim.isEmpty() ) nB.nr( trim );
		}

		final String last = sB.toString().trim();
		if ( !last.isEmpty() ) nB.a( last );

		return nB;
	}

	public List<String> getList()
	{
		final List<String> sL = new ArrayList<String>( sQ.size() + 1 );
		sL.addAll( sQ );
		sL.add( sB.toString() );
		return sL;
	}

	public String[] getArray()
	{
		final String[] sA = new String[ sQ.size() + 1 ];
		sQ.toArray( sA );
		sA[ sA.length - 1 ] = sB.toString();
		return sA;
	}

	public Iterator<String> iterator()
	{
		return getList().iterator();
	}

//  ================================================================================

	public String toString()
	{
		final StringBuilder sB = new StringBuilder();

		for ( final String s : sQ )
		{
			sB.append( s );
			sB.append( config.newLine.getNewLine() );
		}

		return sB.append( this.sB ).toString();
	}

//  ################################################################################

	private static final long serialVersionUID = 0x9568286008441B1DL;
}
