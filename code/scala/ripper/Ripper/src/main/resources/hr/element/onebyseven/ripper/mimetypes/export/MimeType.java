package hr.element.onebyseven.common;

public enum MimeType {
    [[enumeration]][[enum.padding]]("[[extension]]",[[ext.padding]] "[[mimeType]]"[[mime.padding]])[[eol]]

    public final String extension;
    public final String mimeType;

    private MimeType(final String extension, final String mimeType) {
        this.extension = extension;
        this.mimeType = mimeType;
    }

    public static MimeType findByExtension(final String extension) {
        final String name = extension.toUpperCase()
            .replaceFirst("^(\\d)", "_$1")
            .replaceAll("\\W", "_");

        try {
            final MimeType mt = valueOf(name);
            if (mt.extension.equalsIgnoreCase(extension)) {
                return mt;
            }
        }
        catch (final IllegalArgumentException e) {}

        return null;
    }
}
