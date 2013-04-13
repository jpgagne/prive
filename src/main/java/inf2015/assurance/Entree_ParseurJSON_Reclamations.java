package inf2015.assurance;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;


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
 


private ArrayList<EnregistrementJSON_Reclamation> lireLesReclamations() throws ExceptionDonneeInvalide
{
    EnregistrementJSON_Reclamation nouvelleEntreeReclamationJSON;
    Reclamation nouvelleReclamation;
    ArrayList<EnregistrementJSON_Reclamation> listeReclamation = new ArrayList<EnregistrementJSON_Reclamation>();
    
    
    
    
    JSONArray root = (JSONArray) JSONSerializer.toJSON(jsonTxt);
        int documentCount = root.size();
        for (int i = 0; i < documentCount; i++) {
            JSONObject document = root.getJSONObject(i);
            if (document.getString("type").equals("book")) {
                System.out.println(document.getString("title") + " publié en " + document.getInt("year"));
            }
        }
    
    ObjectMapper mapper = new ObjectMapper();
        try {
             
            
            listeReclamation = mapper.readValue(this.fichierInput,new TypeReference<List<EnregistrementJSON_Reclamation>>(){});
            nouvelleEntreeReclamationJSON = mapper.readValue(fichierInput, EnregistrementJSON_Reclamation.class);
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
