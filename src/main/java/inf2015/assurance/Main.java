/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inf2015.assurance;

import java.io.File;

/**
 *
 * @author JP
 */
public class Main {
    
    
    
    
    
    
public static void main(String args[])
{
        File fichierEntree = new File("reclamations.json");
Entree_ParseurJSON_Reclamations entree_ParseurJSON_Reclamations = new Entree_ParseurJSON_Reclamations(fichierEntree)   ; 
}





}
