package hr.element.onebyseven.common;

public enum Country {
  [[a2.code]]("[[a3.code]]", [[numeric3.padding]][[numeric3]:padLeft(3)], "[[name]]"[[name.padding]])[[eol]]

  public final String alpha2, alpha3, numeric3;
  public final String isoName, wikiName;

  private Country(final String alpha3, final int numeric3, final String wikiName) {
    this.alpha2 = name();
    this.alpha3 = alpha3;
    this.numeric3 = String.format("%03d", numeric3);
    this.isoName = wikiName.toUpperCase();
    this.wikiName = wikiName;
  }
}
