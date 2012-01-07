import iso.countries.Table;
import db.dmp.Dumper;
import dmp.Config;

public class EntryPoint
{
	public static void main( final String[] args )
	{
		final Table t = new Table();
		final Config c = new Config();
		
		new Dumper( t, c ).echo();
	}
}
