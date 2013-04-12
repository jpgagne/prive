package inf2015.assurance;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;




public class Entree_ParseurJSON_Reclamations 
{

//<editor-fold defaultstate="collapsed" desc="attributs">


//singletons
private ListeContrats listeContrats;
private CategoriesSoin categoriesSoin;

//in
private  File fichierInput;

//out
//private Evaluateur evaluateur;

//local
private char typeContrat;
private Integer noClient;
private Date moisTraite;
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="constructeurs">
    
Entree_ParseurJSON_Reclamations(File fichierInput) //throws ExceptionDonneeInvalide, ExceptionUsage
    {
    this.categoriesSoin = CategoriesSoin.getInstance();
    this.listeContrats = ListeContrats.getInstance();
    this.fichierInput = fichierInput;

    
    }

//</editor-fold>


public void deQuosse()
{
        try {
            Reclamation nouvelleReclamation = lireUneReclamation();
            System.out.println(nouvelleReclamation);
        } catch (ExceptionDonneeInvalide ex) {
            System.out.println(" KAPLOW!!!!!"+ ex.getMessage());
        }
}


private Reclamation lireUneReclamation() throws ExceptionDonneeInvalide
{
    EntreeReclamationJSON nouvelleEntreeReclamationJSON;
    Reclamation nouvelleReclamation;
    ObjectMapper mapper = new ObjectMapper();
        try {
            nouvelleEntreeReclamationJSON = mapper.readValue(fichierInput, EntreeReclamationJSON.class);
        }  catch (JsonParseException ex) {
            throw new ExceptionDonneeInvalide(ex.getMessage());
        } catch (JsonMappingException ex) {
            throw new ExceptionDonneeInvalide(ex.getMessage());
        } catch (IOException ex) {
            throw new ExceptionDonneeInvalide(ex.getMessage());
        }
    
    nouvelleReclamation = new Reclamation(nouvelleEntreeReclamationJSON);  
    
    return nouvelleReclamation;
    
}


        



    
//<editor-fold defaultstate="collapsed" desc="traitement principal">
    
//protected Evaluateur parserFichierReclamations() throws ExceptionDonneeInvalide, ExceptionUsage
//    {        
//        
//        
//        
//        
//        ObjectMapper mapper = new ObjectMapper();
//        
//        
//        Reclamation reclamation = mapper.readValue(this.fichierInput, Reclamation.class);
//        
//    this.noClient = ?
//    this.typeContrat = ?
//    this.moisTraite = ?
//
//    this.evaluateur = new Evaluateur(getNoClient(), getTypeContrat(), getMoisTraite());
//    return this.evaluateur;
//    }
//
////</editor-fold>
//       
    
//<editor-fold defaultstate="collapsed" desc="methodes utilitaires private">

//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="accesseurs">


//protected char getTypeContrat()
//    {
//    return typeContrat;
//    }
//
//protected Integer getNoClient()
//    {
//    return noClient;
//    }
//
//protected Date getMoisTraite()
//    {
//    return moisTraite;
//    }


//</editor-fold>
}
