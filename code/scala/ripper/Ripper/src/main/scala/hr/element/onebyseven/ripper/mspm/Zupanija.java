package hr.element.onebyseven.ripper.mspm;

public enum Zupanija {
  BBZ( 7, "Bjelovarsko-bilogorska županija", "043"),
  BPZ(12, "Brodsko-posavska županija",       "035"),
  DNZ(19, "Dubrovačko-neretvanska županija", "020"),
  IZ (18, "Istarska županija",               "052"),
  KZ ( 4, "Karlovačka županija",             "047"),
  KKZ( 6, "Koprivničko-križevačka županija", "048"),
  KZZ( 2, "Krapinsko-zagorska županija",     "049"),
  LSZ( 9, "Ličko-senjska županija",          "053"),
  MZ (20, "Međimurska županija",             "040"),
  OBZ(14, "Osječko-baranjska županija",      "031"),
  PSZ(11, "Požeško-slavonska županija",      "034"),
  PGZ( 8, "Primorsko-goranska županija",     "051"),
  SMZ( 3, "Sisačko-moslavačka županija",     "044"),
  SDZ(17, "Splitsko-dalmatinska županija",   "021"),
  SKZ(15, "Šibensko-kninska županija",       "022"),
  VZ ( 5, "Varaždinska županija",            "042"),
  VPZ(10, "Virovitičko-podravska županija",  "033"),
  VSZ(16, "Vukovarsko-srijemska županija",   "032"),
  ZDZ(13, "Zadarska županija",               "023"),
  ZGZ( 1, "Zagrebačka županija",             "01" ),
  GZG(21, "Grad Zagreb",                     "01" );

  public final int sifra;
  public final String naziv;
  public final String pozivniBroj;

  private Zupanija(
      final int sifra,
      final String naziv,
      final String pozivniBroj) {
    this.sifra = sifra;
    this.naziv = naziv;
    this.pozivniBroj = pozivniBroj;
  }
}
