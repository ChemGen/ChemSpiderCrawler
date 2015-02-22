package xml2pdf;

public final class Chemical {
  
  public Chemical(String aSmiles, String aInChI, String aInChIKey) {
    fSmiles = aSmiles;
    fInChI = aInChI;
    fInChIKey = aInChIKey;
  }
  
  @Override
  public String toString() {
    return fSmiles + "\t" + fInChI + "\t" + fInChIKey;
  }
  
  private final String fSmiles;
  private final String fInChI;
  private final String fInChIKey;
  
}
