import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;

public class Traitement 
{  
//private SortieXML sortieXML;
//private Entree_ParseurXML_Reclamations parseurXML_Entree;
private SortieJSON sortieJSON;
private Entree_ParseurJSON_Reclamations parseurJSON_Entree;
private File fichierEntrantReclamations;
private File fichierSortantRemboursements;
private String nomFichierEntrantReclamations;
private String nomFichierSortantRemboursements;


private Evaluateur evaluateur;

private static String pathFichierContrats = "data"+System.getProperty("file.separator") +"contrats.xml";
//private static String pathFichierContratsJSON = "data"+System.getProperty("file.separator") +"contrats.json";
private Map<Character, Contrat> mapContrats;
private ListeContrats listeContrats;
private CategoriesSoin categoriesSoin;


//<editor-fold defaultstate="collapsed" desc="Constructeur et gestion de l'instance singleton">
private static Traitement instance = null;

private Traitement()
    {
    this.categoriesSoin = CategoriesSoin.getInstance();
    this.listeContrats = ListeContrats.getInstance();
    }

protected static Traitement getInstance()
    {
    if(instance == null)
        {
        instance = new Traitement();    
        }
    return instance;
    }
//</editor-fold>


protected void execution (File fichierEntrantReclamations) throws ExceptionIO, FileNotFoundException
    {
    try{
 
        this.fichierEntrantReclamations = fichierEntrantReclamations;
        this.nomFichierEntrantReclamations = this.fichierEntrantReclamations.getName();
        this.nomFichierSortantRemboursements = "data"+System.getProperty("file.separator") +"refunds-"+this.nomFichierEntrantReclamations;  
        this.fichierSortantRemboursements = new File(nomFichierSortantRemboursements);
        this.listeContrats = ListeContrats.getInstance();
        //sortieXML = new SortieXML();
        sortieJSON = new SortieJSON();
        
//        this.parseurXML_Entree = new Entree_ParseurXML_Reclamations(fichierEntrantReclamations);
//        this.evaluateur = parseurXML_Entree.parserFichierReclamations();
//        this.evaluateur.calculerRemboursements();
//        sortieXML.setDossier(parseurXML_Entree.getTypeContrat(), parseurXML_Entree.getNoClient());
//        sortieXML.setMoisTraite(parseurXML_Entree.getMoisTraite());
//        sortieXML.redigerDocumentSortie(evaluateur.listeRemboursements());
//        sortieXML.produireFichierSortie();
        
        this.parseurJSON_Entree = new Entree_ParseurJSON_Reclamations(fichierEntrantReclamations);
        this.evaluateur = parseurJSON_Entree.parserFichierReclamations();
        this.evaluateur.calculerRemboursements();
        sortieJSON.setDossier(parseurJSON_Entree.getTypeContrat(), parseurJSON_Entree.getNoClient());
        sortieJSON.setMoisTraite(parseurJSON_Entree.getMoisTraite());
        sortieJSON.redigerDocumentSortie(evaluateur.listeRemboursements());
        sortieJSON.produireFichierSortie();

        System.out.println("Exécution terminée avec succès");
        }


    catch (ExceptionDonneeInvalide excDI)
        {
        System.out.println("ERREUR - L'exécution se termine avec échec par données invalides"); 
        if (excDI.getMessage().length() > 0)
            {
            System.out.println(excDI.getMessage() + "\n");
            }
        }
    catch (ExceptionUsage excU)
        {
        System.out.println("ERREUR - L'exécution se termine avec échec d'usage");  
        if (excU.getMessage().length() > 0)
            {
            System.out.println(excU.getMessage() + "\n");
            }
        //sortieXML.redigerDocumentSortie(excU);
        }
    }




protected String getNomFichierEntrantContrats()
    {
    return this.pathFichierContrats;
    }

protected String getNomFichierSortant()
    {
    return this.nomFichierSortantRemboursements;
    }

protected File getFichierSortant()
    {
    return this.fichierSortantRemboursements;
    }



}