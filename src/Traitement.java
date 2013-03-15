import java.util.HashMap;
import java.util.Map;


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


private static Traitement instance = null;
private Traitement() 
{

}

public static Traitement getInstance()
    {
    if(instance == null)
        {
        instance = new Traitement();
 
        }
    return instance;
    }

protected void execution(String pathEntrant, String pathSortant) throws ExceptionIO
    {
    this.categoriesSoin = CategoriesSoin.getInstance();
    this.listeContrats = ListeContrats.getInstance();
    this.pathEntrant = pathEntrant;
    this.pathSortant = pathSortant;
    this.mapContrats = new HashMap<>();
    try{
        chargementDesContrats();
        sortieXML  = new SortieXML(this.getPathSortant());
        parseurXML_Entree = new Entree_ParseurXML_Reclamations(this.getPathEntrant());

        evaluateur = parseurXML_Entree.parserFichierReclamations();
//            try {
//                evaluateur.setContrat(parseurXML_Entree.getTypeContrat());
//            } catch (ExceptionContratInexistant excCI) {
//                throw  new ExceptionDonneeInvalide(EnumErreurLecture.CONTRAT_INVALIDE);
//            }
        evaluateur.calculerRemboursements();

        sortieXML.setDossier(parseurXML_Entree.getTypeContrat(), parseurXML_Entree.getNoClient());
        sortieXML.setMoisTraite(parseurXML_Entree.getMoisTraite());
        sortieXML.redigerDocumentSortie(evaluateur.listeRemboursements());
        sortieXML.produireFichierSortie();
        
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
   catch ( ExceptionUsage excU)
    {
    System.out.println("ERREUR - L'exécution se termine avec échec d'usage");  
    if (excU.getMessage().length() > 0)
        {
        System.out.println(excU.getMessage() + "\n");
        }
    sortieXML.redigerDocumentSortie(excU);

    }

    }



private void chargementDesContrats() throws ExceptionDonneeInvalide, ExceptionUsage
    {
    mapContrats = new HashMap<>();
    Entree_ParseurXML_Contrats entree_ParseurXML_Contrats = new Entree_ParseurXML_Contrats(pathFichierContrats);
   // System.out.println("ANTE PARSE CONTRAT");
    try {
        this.mapContrats = entree_ParseurXML_Contrats.parserFichierContrats();
        } catch (ExceptionSoinNonCouvert excSNC) {
            throw new ExceptionDonneeInvalide(excSNC.getMessage());
        }
    //System.out.println("POST PARSE CONTRAT");
    //System.out.println(" Nb contrats charges: "+this.mapContrats.size());
    }

protected boolean contratPresent(Character carTypeContrat)
    {
    return (this.mapContrats.containsKey(carTypeContrat));
    }


protected Map<Character, Contrat> getContrats()
{
    return  this.mapContrats;
}


protected Contrat getContrat(Character carTypeContrat) throws ExceptionContratInexistant
{
    if (this.contratPresent(carTypeContrat))
            {
                return this.mapContrats.get(carTypeContrat);
            }
    throw new ExceptionContratInexistant(carTypeContrat);
}

    /**
     * @return the pathEntrant
     */
    public String getPathEntrant() {
        return pathEntrant;
    }

    /**
     * @return the pathSortant
     */
    public String getPathSortant() {
        return pathSortant;
    }

}
