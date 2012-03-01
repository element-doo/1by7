package hr.element.onebyseven.common.old;

public enum Zupanija {
  GZZ  (21,  "Grad Zagreb",                      "01" ),
  ZGZ  (1,   "Zagrebačka županija",              "01" ),
  DNZ  (19,  "Dubrovačko-neretvanska županija",  "020"),
  SDZ  (17,  "Splitsko-dalmatinska županija",    "021"),
  SKZ  (15,  "Šibensko-kninska županija",        "022"),
  ZZ   (13,  "Zadarska županija",                "023"),
  OBZ  (14,  "Osječko-baranjska županija",       "031"),
  VSZ  (16,  "Vukovarsko-srijemska županija",    "032"),
  PSZ  (10,  "Virovitičko-podravska županija",   "033"),
  BSZ  (11,  "Požeško-slavonska županija",       "034"),
  BPZ  (12,  "Brodsko-posavska županija",        "035"),
  MZ   (20,  "Međimurska županija",              "040"),
  VZ   (5,   "Varaždinska županija",             "042"),
  BBZ  (7,   "Bjelovarsko-bilogorska županija",  "043"),
  SMZ  (3,   "Sisačko-moslavačka županija",      "044"),
  KZ   (4,   "Karlovačka županija",              "047"),
  KKZ  (6,   "Koprivničko-križevačka županija",  "048"),
  KZZ  (2,   "Krapinsko-zagorska županija",      "049"),
  PGZ  (8,   "Primorsko-goranska županija",      "051"),
  IZ   (18,  "Istarska županija",                "052"),
  LSZ  (9,   "Ličko-senjska županija",           "053");

  public final String imeZupanije, pozivniBroj;
  public final int sifra;
  private Zupanija(
      int    sifra
    , String ime
    , String pozivniBroj){
    this.sifra       = sifra;
    this.imeZupanije = ime;
    this.pozivniBroj = pozivniBroj;
  }
}