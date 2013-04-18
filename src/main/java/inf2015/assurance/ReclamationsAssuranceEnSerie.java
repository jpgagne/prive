package inf2015.assurance;
import java.io.File;


public class ReclamationsAssuranceEnSerie
{
public static final String pathFichiersReclamation = "fichiersReclamation"+System.getProperty("file.separator");
public static void main(String args[])
    {
    int intNbArgs = args.length;
    System.out.println("Start run");
    try {
        if (intNbArgs != 0) 
            {
            throw new ExceptionIO("NB arguments = " + intNbArgs + " / doit être = 0");
            }
        File fichierSortie;
        Sortie_JSON sortie_JSON = Sortie_JSON.getInstance();
        
     Entree_ParseurJSON_Reclamations epjsonr;
        CategoriesContrat categoriesContrat = CategoriesContrat.getInstance();
        categoriesContrat.chargerContrats();

    File repertoire = new File(pathFichiersReclamation);
    File[] listeDesFichiers = repertoire.listFiles(); 
    File fichierTrouve;
    String strNomFichierEntree;
    
    if (!(listeDesFichiers.length > 0))
        {
        throw new ExceptionUsage("Aucun fichier reclamations trouvé dans le répertoire "+pathFichiersReclamation);
        }
    for (int i = 0; i < listeDesFichiers.length; i++) 
        {
        fichierTrouve = listeDesFichiers[i];
        if (fichierTrouve.isFile()) 
            {
            strNomFichierEntree = fichierTrouve.getName();
            if (strNomFichierEntree.endsWith(".json") || strNomFichierEntree.endsWith(".JSON"))
                {
                    System.out.println("Fichier "+fichierTrouve.getName()+" Ouvert");
                    String nomFichierSortie = "REMBOURSEMENTS_"+fichierTrouve.getName();
                    fichierSortie = new File(nomFichierSortie);
                    sortie_JSON.assignerFichierSortie(fichierSortie);
                    sortie_JSON.assignerFichierSortie(fichierSortie);
                epjsonr = new Entree_ParseurJSON_Reclamations(fichierTrouve);
                }
            } 
        }   
    }      
    catch (ExceptionUsage excU)
    {
        System.out.println(excU.getMessage());
    }
    catch (ExceptionIO excIO)
        {
        System.out.println("ERREUR - L'exécution se termine avec échec critique");
        if (excIO.getMessage().length() > 0)
            {
            System.out.println(excIO.getMessage() + "\n");
            }
        }
    catch (ExceptionSpecifique excSpec)
        {
        System.out.println("Execution terminee avec erreur");
        excSpec.afficherException();
        }
    }
}