package org.lby7.tables;

public enum Country
{
$(enums)
    public final String a2, a3, n3;
    public final String isoName, wikiName;

    private Country( final String a3, final short n3, final String wikiName )
    {
        this.a2 = name();
        this.a3 = a3;
        this.n3 = String.format( "%03d", n3 );
        this.isoName = wikiName.toUpperCase();
        this.wikiName = wikiName;
    }
}