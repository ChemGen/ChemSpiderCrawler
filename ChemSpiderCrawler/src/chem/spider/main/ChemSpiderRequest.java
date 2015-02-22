package chem.spider.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public final class ChemSpiderRequest {
  
  public ChemSpiderRequest(int aId, String aToken) {
    fId = aId;
    fToken = aToken;
  }
  
  public String getCompoundInfo() {
    StringBuffer result = new StringBuffer();
    
    URL url = genURL();
    HttpURLConnection c = connectToURL(url);
    int code = handleResponseCode(c);
    if (code == 500) return "Invalid ID";
    
    try {
      BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
      
      for (String line = br.readLine(); line != null; line = br.readLine()) {
        result.append(line).append("\n");
      }
      
    } catch (IOException e) {
      throw new RuntimeException("IO Error reading from connection");
    }
    
    return result.toString();
  }
  
  /// Private
  
  private final int fId;
  private final String fToken; 
  
  private static final String host = "http://www.chemspider.com";
  private static final String api = "/Search.asmx/GetCompoundInfo";
  
  private URL genURL() {
    StringBuffer urlSB = new StringBuffer();
    urlSB.append(host)
         .append(api)
         .append("?")
         .append(genQuery("CSID", fId))
         .append("&")
         .append(genQuery("token", fToken));
    URL url;
    try {
      url = new URL(urlSB.toString());
    } catch (MalformedURLException e) {
      throw new IllegalStateException("Malformed URL:" + urlSB.toString());
    }
    return url;
  }
  
  private static StringBuffer genQuery(String field, Object o) {
    StringBuffer result = new StringBuffer();
    result.append(field)
          .append("=")
          .append(o.toString());
    return result;
  }
  
  private static HttpURLConnection connectToURL(URL url) {
    HttpURLConnection c;
    try {
      c = (HttpURLConnection) url.openConnection();
    } catch (IOException e) {
      throw new IllegalStateException("Unable to read from URL");
    }
    try {
      c.setRequestMethod("GET");
    } catch (ProtocolException e) {
      throw new IllegalStateException("Protocol exception");
    }
    return c;
  }
  
  private static int handleResponseCode(HttpURLConnection c) {
    int responseCode;
    try {
      responseCode = c.getResponseCode();
    } catch (IOException e) {
      throw new RuntimeException("IO Error reading response code from connection");
    }
    switch (responseCode) {
      case 200:
      case 500:
        return responseCode;
      default:
        throw new RuntimeException("HTTP Error code: " + responseCode);
    }
  }
  
}
