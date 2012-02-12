import iso.countries.IsoCountries;
import iso.countries.dmp.IsoCountriesDumper;
import dmp.Config;


public class EntryPoint
{
  public static void main( final String[] args )
  {
    final Config config = new Config();
    final IsoCountries table = new IsoCountries();

    System.out.println( new IsoCountriesDumper( config, table ) );
  }
}
