package iso.countries.dmp.fields;

import dmp.Config;
import dmp.Configurable;

public class Numeric3CodeName extends Configurable
{
  private final String numeric3CodeName;

  public Numeric3CodeName( final Config config, final String numeric3CodeName )
  {
    super( config );
    this.numeric3CodeName = numeric3CodeName;
  }

  public String getNumeric3CodeName()
  {
    return numeric3CodeName;
  }
}
