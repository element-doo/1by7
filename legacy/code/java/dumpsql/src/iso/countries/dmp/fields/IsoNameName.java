package iso.countries.dmp.fields;

import dmp.Config;
import dmp.Configurable;

public class IsoNameName extends Configurable
{
  private final String isoNameName;

  public IsoNameName( final Config config, final String isoNameName )
  {
    super( config );
    this.isoNameName = isoNameName;
  }

  public String getIsoNameName()
  {
    return isoNameName;
  }
}
