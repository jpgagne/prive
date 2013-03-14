import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Traitement 
{
    
private SortieXML sortieXML;
private Entree_ParseurXML_Reclamations parseurXML_Entree;
private Evaluateur evaluateur;
private String pathEntrant;
private String pathSortant;

private static String pathFichierContrats = "data"+System.getProperty("file.separator") +"contrats.xml";
private Map<Character, Contrat> mapContrats;
private CategoriesSoin categoriesSoin;
private ListeContrats listeContrats;

Traitement(String pathEntrant, String pathSortant) throws ExceptionIO
    {
    categoriesSoin.getClass();  
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
    mapContrats = new HashMap<>();
    Entree_ParseurXML_Contrats entree_ParseurXML_Contrats = new Entree_ParseurXML_Contrats(pathFichierContrats);
    System.out.println("ANTE PARSE CONTRAT");
    try {
        this.mapContrats = entree_ParseurXML_Contrats.parserFichierContrats();
        } catch (ExceptionSoinNonCouvert excSNC) {
            throw new ExceptionDonneeInvalide(excSNC.getMessage());
        }
    System.out.println("POST PARSE CONTRAT");
    System.out.println(" Nb contrats charges: "+this.mapContrats.size());
    }


public boolean contratPresent(Character carTypeContrat)
    {
    return (this.mapContrats.containsKey(carTypeContrat));
    }






}
