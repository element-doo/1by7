package export;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;

public class Dumper implements Iterable<Dumpable>
{
	public Queue<Dumpable> dL;
	
	public Dumper()
	{
		dL = new ArrayDeque<Dumpable>();
	}
	
	public boolean add( final Dumpable d )
	{
		return dL.add( d );
	}

	@Override
	public Iterator<Dumpable> iterator()
	{
		return dL.iterator();
	}
}
