package chem.spider.main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public final class ChemSpider {
  
  
  
  public static void main(String[] args) {
    
    int min = Integer.parseInt(args[1]);
    int max = min + 1000;
    
    for (int i = min; i < max; i++) {
      ChemSpiderRequest r = new ChemSpiderRequest(i, args[0]);
      String compoundInfo = r.getCompoundInfo();
      
      if (compoundInfo.equals("Invalid ID")) {
        System.out.println("Invalid ID: " + i);
        continue;
      }
      
      File file = new File("tmp/" + i + ".xml");
      try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
        bw.write(compoundInfo);
      } catch (IOException e) {
        throw new RuntimeException("Unable to write to file: " + file);
      }
      System.out.println(file.toString());
      try {
        Thread.sleep(1000); // Delay to keep from overloading server.
      } catch(InterruptedException ex) {
        Thread.currentThread().interrupt();
      }
    }
    
    
  }

}
