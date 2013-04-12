/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inf2015.assurance;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author JP
 */
public class TestJason {
 
    

    public static void main( String[] args )
    {

            System.out.println( "test JSON" );
            ObjectMapper mapper = new ObjectMapper();
            File fichierEntree = new File("reclamationUnique.json");
    
          
            Entree_ParseurJSON_Reclamations wassup = new Entree_ParseurJSON_Reclamations(fichierEntree);
            
            wassup.deQuosse();
            
       
       
                    }
        
    
}