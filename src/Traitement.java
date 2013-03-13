
import java.util.logging.Level;
import java.util.logging.Logger;


public class Traitement {
    
    
private SortieXML sortieXML;
private Entree_ParseurXML parseurXML_Entree;
private Evaluateur evaluateur;
private String pathEntrant;
private String pathSortant;



Traitement(String pathEntrant, String pathSortant) throws ExceptionIO
    {
    this.pathEntrant = pathEntrant;
    this.pathSortant = pathSortant;
    try{
        sortieXML  = new SortieXML(pathSortant);
        parseurXML_Entree = new Entree_ParseurXML(pathEntrant);
        evaluateur = parseurXML_Entree.parserFichierReclamations();
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

}
