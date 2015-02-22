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
  
  private static Chemical getChemical(Document d) {
    String inChI = d.getFirstChild().getChildNodes().item(3).getTextContent();
    String inChIKey = d.getFirstChild().getChildNodes().item(5).getTextContent();
    String smiles = d.getFirstChild().getChildNodes().item(7).getTextContent();
    return new Chemical(smiles, inChI, inChIKey);
  }
  
  public static void main(String[] args) {
    
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(outFile))) {
      bw.write("smiles\tInChI\tInChIKey\n");
      for (int i = 1; i < 4001; i++) {
        File f = new File("tmp/" + i + ".xml");
        if (!f.exists()) continue;
        System.out.println(f);
        
        Document d;
        try {
          d = b.parse(f);
        } catch (SAXException e) {
          e.printStackTrace();
          throw new RuntimeException("SAX Exception while reading document.");
        } catch (IOException e) {
          e.printStackTrace();
          throw new RuntimeException("Unable to read document.");
        }
        
        Chemical c = getChemical(d);
        bw.write(c.toString());
        bw.write("\n");
      }
      bw.close();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
  }

}
