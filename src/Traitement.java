
import java.util.ArrayList;



public class Traitement {
    
    
private SortieXML sortieXML;
private Entree_ParseurXML_Reclamations parseurXML_Entree;
private Evaluateur evaluateur;
private String pathEntrant;
private String pathSortant;

private static String pathFichierContrats = "data"+System.getProperty("file.separator") +"contrats.xml";
private ArrayList<Contrat> listeContrats;

Traitement(String pathEntrant, String pathSortant) throws ExceptionIO
    {
    this.pathEntrant = pathEntrant;
    this.pathSortant = pathSortant;
    try{
        chargementDesContrats();
        sortieXML  = new SortieXML(this.pathSortant);
        parseurXML_Entree = new Entree_ParseurXML_Reclamations(this.pathEntrant);
                System.out.println("PARSEUR OK");
        evaluateur = parseurXML_Entree.parserFichierReclamations();
        System.out.println("EVALUATEUR OK");
        sortieXML.setDossier(parseurXML_Entree.getTypeContrat(), parseurXML_Entree.getNoClient());
        sortieXML.setMoisTraite(parseurXML_Entree.getMoisTraite());
        sortieXML.redigerDocumentSortie(evaluateur.listeRemboursements());

        System.out.println("Exécution terminée avec succès");
    }
    catch (ExceptionDonneeInvalide excDI)
        {
        System.out.println("ERREUR - L'exécution se termine avec échec par données invalides"); 
        if (excDI.getMessage().length() > 0)
        {
        System.out.println(excDI.getMessage() + "\n");
        }
    sortieXML.redigerDocumentSortie(excDI);

        }
   catch ( ExceptionUsage excU)
    {
    System.out.println("ERREUR - L'exécution se termine avec échec d'usage");  
    if (excU.getMessage().length() > 0)
        {
        System.out.println(excU.getMessage() + "\n");
        }
    sortieXML.redigerDocumentSortie(excU);

    }
    finally
        {
        sortieXML.produireFichierSortie();
        }
    }
private void chargementDesContrats() throws ExceptionDonneeInvalide, ExceptionUsage
{
    listeContrats = new ArrayList<>();
    Entree_ParseurXML_Contrats entree_ParseurXML_Contrats = new Entree_ParseurXML_Contrats(pathFichierContrats);
    System.out.println("ANTE PARSE CONTRAT");
    entree_ParseurXML_Contrats.parserFichierContrats();
    System.out.println("POST PARSE CONTRAT");
}
}
