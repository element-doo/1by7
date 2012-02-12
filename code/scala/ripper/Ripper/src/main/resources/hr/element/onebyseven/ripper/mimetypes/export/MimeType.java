package hr.element.onebyseven.common;

public enum MimeType {
  [[enumeration]][[enum.padding]]("[[extension]]",[[ext.padding]] "[[mimeType]]"[[mime.padding]])[[eol]]

  public final String extension;
  public final String mimeType;

  private MimeType(final String extension, final String mimeType) {
    this.extension = extension;
    this.mimeType = mimeType;
  }
}
