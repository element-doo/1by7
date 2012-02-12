package db.dmp.cfg;

import dmp.Config;

public class Encoding
{
  public static final String UTF8 = "UTF-8";
  public static final String ISO88591 = "ISO-8859-1";
  public static final String CP1250 = "windows-1250";
  public static final String ASCII = "US-ASCII";

  private final Config config;
  private final String encoding;

  public Encoding( final Config config, final String encoding )
  {
    this.config = config;
    this.encoding = encoding;
  }

  public String getEncoding()
  {
    return encoding;
  }
}
