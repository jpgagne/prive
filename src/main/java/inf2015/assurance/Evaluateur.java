package inf2015.assurance;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;


public class Evaluateur 
{
    
private ArrayList<Reclamation> listeReclamations;
private ArrayList<Remboursement> listeRemboursements;
    
private Argent total;
private Date moisTraite;
private Character carTypeContrat;
private Integer noClient;
private Contrat contrat;


private Evaluateur()
{
}

Evaluateur( Integer noClient, char charContrat, Date moisTraite, ArrayList<Reclamation> listeReclamations)
    {
    this.carTypeContrat = charContrat;
    this.noClient = noClient;
    this.moisTraite = moisTraite; 
    this.listeReclamations = listeReclamations;
    this.initialiser();
    this.calculerRemboursements();
    }

private void initialiser()
    {

    try {
        CategoriesContrat categoriesContrat = CategoriesContrat.getInstance();
        this.contrat = categoriesContrat.trouverContrat(carTypeContrat);
        }
    catch (ExceptionContratInexistant excCI) 
        {
            // Rien. C'est déjà validé à cette étape.
        }
    this.listeRemboursements = new ArrayList<Remboursement>();
    SimpleDateFormat formatDate = new SimpleDateFormat("MMMM yyyy", Locale.FRENCH);
    System.out.println("Nouvel evaluateur cree pour" + System.getProperty("line.separator")
                        + "   client: "+ noClient+ System.getProperty("line.separator")
                        + "   contrat: "+ contrat.getTypeContrat()+ System.getProperty("line.separator")
                        + "   mois: "+formatDate.format(moisTraite));
    }


private void calculerRemboursements()
    {
    System.out.println("Calculer Remboursements ()");
    total = new Argent(0);
    for (Iterator<Reclamation> it = listeReclamations.iterator(); it.hasNext();)
        {
        Reclamation reclamation = it.next();
        try {
            Remboursement remboursement = calculerRemboursement(reclamation);
            this.listeRemboursements.add(remboursement);
            this.total.additionner(remboursement.getMontant()) ;
            } 
        catch (ExceptionSoinNonCouvert excSNC) 
            {
            // Rien. On continue sans approuver celle-ci.
            }
        }
    }


private Remboursement calculerRemboursement(Reclamation reclamation) throws ExceptionSoinNonCouvert  
{

System.out.println("calculerRemboursement()");
System.out.println(this.contrat);
System.out.println(reclamation);

Argent montantReclame = reclamation.getMontantReclame();

Couverture couverture = this.contrat.trouverCouvertureParNoSoin(reclamation.getNoSoin());
CategoriesBeneficiaire categoriesBeneficiaire = CategoriesBeneficiaire.getInstance();
Beneficiaire beneficiaire = categoriesBeneficiaire.trouverBeneficiaire(reclamation.getCodeTypeBeneficiaire());
System.out.println(beneficiaire);
Remboursement nouveauRemboursement = new Remboursement(reclamation.getNoSoin(), reclamation.getDateSoin(), onPaieCombien(montantReclame, couverture, beneficiaire ));
System.out.println(nouveauRemboursement);
return nouveauRemboursement;
    
}

private Argent onPaieCombien(Argent montantDemande, Couverture couverture, Beneficiaire beneficiaire)
    {       
    int intPourcentage = (int) Math.round(couverture.getPourcentage()*100);
    montantDemande.multiplierPar(intPourcentage);
    montantDemande.multiplierPar(beneficiaire.getPourcentage());
    montantDemande.diviserPar(10000);
    if ((couverture.aValeurMax()) &
        ( montantDemande.getMontantCentimes() >=  couverture.getValeurMax().getMontantCentimes()))
        {
        return  couverture.getValeurMax();
        }
    else
        {
        return montantDemande;
        }     
    }


//<editor-fold defaultstate="collapsed" desc="getters">
public Argent getTotal()
{
    return total;
}

public Date getMoisTraite()
{
    return moisTraite;
}

public Contrat getContrat()
{
    return contrat;
}

public Integer getNoClient()
{
    return noClient;
}

}
//</editor-fold>
