package db.dmp.cfg;

import dmp.Config;

public class FormattedInserts
{
  private final Config config;
  private final boolean formatted;

  public FormattedInserts( final Config config, final boolean formatted )
  {
    this.config = config;
    this.formatted = formatted;
  }

  public boolean isNamed()
  {
    return formatted;
  }
}
