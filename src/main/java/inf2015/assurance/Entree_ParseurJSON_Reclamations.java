package inf2015.assurance;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;


public class Entree_ParseurJSON_Reclamations 
{

//<editor-fold defaultstate="collapsed" desc="attributs">

public static final String JSON_KEY_DOSSIER = "Dossier";
public static final String JSON_KEY_MOIS = "Mois";
public static final String JSON_KEY_RECLAMATIONS = "Reclamation";

public static final String JSON_KEY_SOIN = "Soin";
public static final String JSON_KEY_CODE = "Code";
public static final String JSON_KEY_DATE = "Date";
public static final String JSON_KEY_MONTANT = "Montant";




private CategoriesContrat categoriesContrat;
private CategoriesSoin categoriesSoin;
private CategoriesBeneficiaire categoriesBeneficiaire;

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
    
Entree_ParseurJSON_Reclamations(File fichierInput)
    {
    this.categoriesSoin = CategoriesSoin.getInstance();
    this.categoriesContrat = CategoriesContrat.getInstance();
    this.categoriesBeneficiaire = CategoriesBeneficiaire.getInstance();
    this.fichierInput = fichierInput;
        try {
            Reclamation nouvelleReclamation = new Reclamation(parserFichier());
            System.out.println(nouvelleReclamation);
        } catch (ExceptionDonneeInvalide ex) {
             System.out.println("FUCK 4");
                System.out.println(ex.getMessage());
        }
    
    }

//</editor-fold>
 
private EnregistrementJSON_Reclamation parserFichier()
{
    ObjectMapper mapper = new ObjectMapper();

EnregistrementJSON_Reclamation vejson;
        try {
            vejson = mapper.readValue(fichierInput, EnregistrementJSON_Reclamation.class);
            return vejson;
        } catch (JsonGenerationException ex) {
            System.out.println("FUCK 1");
            System.out.println(ex.getMessage());
        } catch (JsonMappingException ex) {
               System.out.println("FUCK 2");
               System.out.println(ex.getMessage());
        }
        catch (IOException ex) {
                System.out.println("FUCK 3");
                System.out.println(ex.getMessage());
        }   
        
        return null;
        
}

private ArrayList<Reclamation> lireLesReclamations() throws ExceptionDonneeInvalide
    {
    EnregistrementJSON_Reclamation nouvelleEntreeReclamationJSON;
    Reclamation nouvelleReclamation;
    ArrayList<Reclamation> listeReclamation = new ArrayList<Reclamation>();
    

    
    JSONObject root = (JSONObject) JSONSerializer.toJSON(this.fichierInput);
    JSONObject dossier = root.getJSONObject(JSON_KEY_DOSSIER);
    JSONObject mois = root.getJSONObject(JSON_KEY_MOIS);
    JSONArray reclamations = root.getJSONArray(JSON_KEY_RECLAMATIONS);
    
    

    int documentCount = reclamations.size();
    for (int i = 0; i < documentCount; i++) {
        JSONObject reclamation = reclamations.getJSONObject(i);
    //    nouvelleReclamation = validerReclamation(reclamation);
     //   listeReclamation.add(nouvelleReclamation);
    }
    return listeReclamation;
    }
        
        

private void validerDossier(JSONObject objetDossier)
{
    
}

private void validerMois(JSONObject objetMois)
{
    
}
//private Reclamation validerReclamation(JSONObject reclamationLue)
//{


//
//
//JSONObject objetSoin;
//JSONObject objetCode;
//JSONObject objetDate;
//JSONObject objetMontant;
//
//    
//
//objetSoin = reclamationLue.getJSONObject(JSON_KEY_SOIN);
//objetCode = reclamationLue.getJSONObject(JSON_KEY_CODE);
//objetDate = reclamationLue.getJSONObject(JSON_KEY_DATE);
//objetMontant = reclamationLue.getJSONObject(JSON_KEY_MONTANT);
//
//        try {
//             
//            
//            listeReclamation = mapper.readValue(this.fichierInput,new TypeReference<List<EnregistrementJSON_Reclamation>>(){});
//            nouvelleEntreeReclamationJSON = mapper.readValue(fichierInput, EnregistrementJSON_Reclamation.class);
//        }  catch (JsonParseException ex) {
//            throw new ExceptionDonneeInvalide(ex.getMessage());
//        } catch (JsonMappingException ex) {
//            throw new ExceptionDonneeInvalide(ex.getMessage());
//        } catch (IOException ex) {
//            throw new ExceptionDonneeInvalide(ex.getMessage());
//        }
//    
//    nouvelleReclamation = new Reclamation(nouvelleEntreeReclamationJSON);  
//    
//    return nouvelleReclamation;
//    
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


