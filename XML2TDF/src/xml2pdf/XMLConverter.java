package xml2pdf;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public final class XMLConverter {
  
  private static final File outFile = new File("output.txt");
  
  private static DocumentBuilder b;
  static {
    try {
      b = DocumentBuilderFactory.newInstance().newDocumentBuilder();
    } catch (ParserConfigurationException e) {
      throw new RuntimeException();
    }
  }
  
  private static String getTextFromChild(Document d, int childNum) {
    return d.getFirstChild().getChildNodes().item(childNum).getTextContent();
  }
  
  private static Chemical getChemical(File f) {
    System.out.println(f);
    
    Document d;
    try {
      d = b.parse(f);
      String inChI = getTextFromChild(d, 3);
      String inChIKey = getTextFromChild(d, 5);
      String smiles = getTextFromChild(d, 7);
      return new Chemical(smiles, inChI, inChIKey);
    } catch (SAXException e) {
      e.printStackTrace();
      throw new RuntimeException("SAX Exception while reading " + f);
    } catch (IOException e) {
      e.printStackTrace();
      throw new RuntimeException("Unable to read " + f);
    }
  }
  
  public static void main(String[] args) {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(outFile))) {
      bw.write("smiles\tInChI\tInChIKey\n");
      for (int i = 1; i < 4001; i++) {
        File f = new File("tmp/" + i + ".xml");
        if (!f.exists()) continue;
        Chemical c = getChemical(f);
        bw.write(c.toString());
        bw.write("\n");
      }
      bw.close();
    } catch (IOException e) {
      throw new RuntimeException("IO Error while writing to " + outFile);
    }
  }

}
