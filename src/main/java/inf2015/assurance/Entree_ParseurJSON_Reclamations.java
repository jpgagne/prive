package inf2015.assurance;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;

public class Entree_ParseurJSON_Reclamations 
{

//<editor-fold defaultstate="collapsed" desc="attributs">

public static final String JSON_KEY_DOSSIER = "dossier";
public static final String JSON_KEY_MOIS = "mois";
public static final String JSON_KEY_RECLAMATIONS = "reclamations";

public static final String JSON_KEY_SOIN = "soin";
public static final String JSON_KEY_CODE = "code";
public static final String JSON_KEY_DATE = "date";
public static final String JSON_KEY_MONTANT = "montant";




private CategoriesContrat categoriesContrat;
private CategoriesSoin categoriesSoin;
private CategoriesBeneficiaire categoriesBeneficiaire;

//in
private File fichierInput;
private String strImput = null;
private boolean dossierLu, moisLu, reclamationsLues = false;
//out
private Evaluateur evaluateur;

//local
private char typeContrat;
private Integer noClient;
private Date moisTraite;
private ArrayList<EnregistrementJSON_Reclamation> listeLue;
private ArrayList<Reclamation> listeReclamations;

//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="constructeurs">
    
Entree_ParseurJSON_Reclamations(File fichierInput) throws ExceptionSpecifique
    {
    this.categoriesSoin = CategoriesSoin.getInstance();
    this.categoriesContrat = CategoriesContrat.getInstance();
    this.categoriesBeneficiaire = CategoriesBeneficiaire.getInstance();
    this.fichierInput = fichierInput;
    this.chargerContenuFichier();
    this.parserContenuFichierImput();
    this.validerListeReclamations();
    this.creerEvaluateur();
    
    
    System.out.println("Nb de reclamations lues: "+ this.listeLue.size());
    System.out.println("Nb de reclamations valides: "+this.listeReclamations.size());
    }

//</editor-fold>
 

private Evaluateur creerEvaluateur()
{
    this.evaluateur = new Evaluateur(noClient, typeContrat, moisTraite, listeReclamations);
    return evaluateur;
}

private void chargerContenuFichier() throws ExceptionIO
    {
    this.strImput = "";
    FileInputStream fis = null;
    try 
        {
        fis = new FileInputStream(fichierInput);
        try 
            {
            FileChannel fc = fis.getChannel();
            MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
            this.strImput = Charset.defaultCharset().decode(bb).toString();
            }
        finally
            {
            fis.close();
            }
        }
    catch (FileNotFoundException excFNF)
        {
        throw new ExceptionIO(fichierInput, EnumCodeErreur.FICHIER_INTROUVABLE, excFNF.getMessage());
        }  
    catch (IOException excIO)
        {
        throw new ExceptionIO(fichierInput, excIO.getMessage());
        }
    }


private void parserContenuFichierImput() throws  ExceptionContratInexistant, ExceptionParseur, ExceptionIO, ExceptionValeurDedoublee
    {
    listeLue = new ArrayList<EnregistrementJSON_Reclamation>();
    JsonFactory jfactory = new JsonFactory();
        try{
        JsonParser jsonParser = jfactory.createJsonParser(this.strImput);

        while (jsonParser.nextToken() != JsonToken.END_OBJECT)
            {
            jsonParser.nextToken();
            String fieldname = jsonParser.getCurrentName();
            if (fieldname == null)break;
            if (fieldname.equals(JSON_KEY_DOSSIER))
                {
                jsonParser.nextToken();
                this.LireCodeDossier(jsonParser.getText());
                }
            if (fieldname.equals(JSON_KEY_MOIS))
                {
               // jsonParser.nextToken();
                this.LireMoisTraite(jsonParser.getText());
                }

            if (fieldname.equals(JSON_KEY_RECLAMATIONS))
                {
                jsonParser.nextToken();
                while (jsonParser.nextToken() != JsonToken.END_ARRAY)
                    {
                    parserUneReclamation(jsonParser);
                    }
                }
            } 
        jsonParser.close();
        }
    catch (IOException excIO)
        {
        throw new ExceptionIO(excIO.getMessage());  
        }
    }


private void parserUneReclamation(JsonParser parser) throws IOException, ExceptionParseur
    {
     System.out.print("NOUVELLE RECLAMATION LUE: "); 
    
     EnregistrementJSON_Reclamation ejsonr = new EnregistrementJSON_Reclamation();
     while (parser.nextToken() != JsonToken.END_OBJECT)
        {
        String fieldname = parser.getCurrentName();
        if (fieldname == null)break;
        
        EnumBalisesJSON baliseJSON = EnumBalisesJSON.valueOf(fieldname.toUpperCase());
        switch (baliseJSON)
            {
            case SOIN:
                {
                ejsonr.soin = parser.getText();
                break;
                }
            case CODE:
                {
                ejsonr.code = parser.getText();  
                break;
                }
            case DATE:
                {
                ejsonr.date = parser.getText(); 
                break;
                }
            case MONTANT:
                {
                ejsonr.montant = parser.getText();
                break;
                }
            default:
                {
                throw new ExceptionParseur(EnumCodeErreur.FICHIERENTRANT_STRUCTUREINTERNEINADEQUATE, fieldname);
                }
            }  
        } // End While
     System.out.println(ejsonr);
     this.listeLue.add(ejsonr);
    }


private void validerListeReclamations() throws ExceptionDonneeInvalide
    {
    this.listeReclamations = new ArrayList<Reclamation>();
    Reclamation nouvelleReclamation;
    for (Iterator<EnregistrementJSON_Reclamation> it = listeLue.iterator(); it.hasNext();)
        {
        EnregistrementJSON_Reclamation enregistrementJSON_Reclamation = it.next();
        nouvelleReclamation = new Reclamation(enregistrementJSON_Reclamation);
        listeReclamations.add(nouvelleReclamation);
        System.out.println("Reclamation validee:");
        System.out.println(nouvelleReclamation);
        }
}


private void LireCodeDossier(String strDossier) throws ExceptionContratInexistant, ExceptionParseur, ExceptionValeurDedoublee
    {
    if (this.dossierLu)
        {
        throw new ExceptionValeurDedoublee("DOSSIER");
        }
    this.dossierLu = true;
        
    if (strDossier.isEmpty())
        {
        throw new ExceptionParseur(EnumCodeErreur.DOSSIER_ABSENT, strDossier);
        }
     if (strDossier.length()!= 7) 
        {
        throw new ExceptionParseur(EnumCodeErreur.DOSSIER_INVALIDE, strDossier);
        }        
     
    this.typeContrat = strDossier.charAt(0);
    if (!categoriesContrat.contratPresent(this.typeContrat))
        {
        throw new ExceptionContratInexistant(this.typeContrat);
        }
    
    this.noClient = ParseurNombres.parseChainePourInteger(strDossier.substring(1));
    }


private void LireMoisTraite(String strMois) throws ExceptionParseur, ExceptionValeurDedoublee
    {
    if (this.moisLu)
        {
        throw new ExceptionValeurDedoublee("MOIS");
        }
    this.moisLu = true;
        
    if (strMois.isEmpty())
        {
        throw new ExceptionParseur(EnumCodeErreur.DATE_ABSENT, strMois);
        }
    
    this.moisTraite = ParseurNombres.parseChainePourDateMois(strMois);
    }

        

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


