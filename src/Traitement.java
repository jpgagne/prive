
import java.util.logging.Level;
import java.util.logging.Logger;


public class Traitement {
    
    
private XML_Output sortieXML;
private ParseurXML_Imput parseurXML_Imput;
private Evaluateur evaluateur;
private String pathEntrant;
private String pathSortant;



Traitement(String pathEntrant, String pathSortant) throws ExceptionIO
    {
    this.pathEntrant = pathEntrant;
    this.pathSortant = pathSortant;
    try{
        sortieXML  = new XML_Output(pathSortant);
        parseurXML_Imput = new ParseurXML_Imput(pathEntrant);
        evaluateur = parseurXML_Imput.parserFichierReclamations();

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
