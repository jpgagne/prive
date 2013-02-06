/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reclamationsassurance;

import java.util.List;



public class TestRead {
  public static void main(String args[]) {
      System.out.println("Start run");
      //int count = args.length;
      System.out.println("Nb d'Arguments: "+ args.length);

      XmlAssurancesParser parser = new XmlAssurancesParser( (String) args[0], (String) args[1]);
  


        
        System.out.println("End Run");
  }
} 
