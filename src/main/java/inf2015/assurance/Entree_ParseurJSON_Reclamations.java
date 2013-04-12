package inf2015.assurance;

import java.io.File;
import java.util.Date;


public class Entree_ParseurJSON_Reclamations 
{

//<editor-fold defaultstate="collapsed" desc="attributs">


//singletons
private ListeContrats listeContrats;
private CategoriesSoin categoriesSoin;

//in
private  File fichierInput;

//out
private Evaluateur evaluateur;

//local
private char typeContrat;
private Integer noClient;
private Date moisTraite;
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="constructeurs">
    
Entree_ParseurJSON_Reclamations(File fichierInput) throws ExceptionDonneeInvalide, ExceptionUsage
    {
    this.categoriesSoin = CategoriesSoin.getInstance();
    this.listeContrats = ListeContrats.getInstance();
    this.fichierInput = fichierInput;
    }

//</editor-fold>

    
//<editor-fold defaultstate="collapsed" desc="traitement principal">
    
protected Evaluateur parserFichierReclamations() throws ExceptionDonneeInvalide, ExceptionUsage
    {        
    this.noClient = ?
    this.typeContrat = ?
    this.moisTraite = ?

    this.evaluateur = new Evaluateur(getNoClient(), getTypeContrat(), getMoisTraite());
    return this.evaluateur;
    }

//</editor-fold>
       
    
//<editor-fold defaultstate="collapsed" desc="methodes utilitaires private">
?
?
?
?
?
?
?
?
?
?
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
