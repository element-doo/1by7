package iso.countries.dmp.fields;

import dmp.Config;
import dmp.Configurable;

public class IsoCountriesName extends Configurable
{
  private final String isoCountriesName;

  public IsoCountriesName( final Config config, final String isoCountriesName )
  {
    super( config );
    this.isoCountriesName = isoCountriesName;
  }

  public String getIsoCountriesName()
  {
    return isoCountriesName;
  }
}
