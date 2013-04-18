package inf2015.assurance;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Evaluateur 
{
    
private ArrayList<Reclamation> listeReclamations;
private ArrayList<Remboursement> listeRemboursements;
    
private Argent total;
private Date moisTraite;
private Character carTypeContrat;
private Integer noClient;
private Contrat contrat;


private CategoriesSoin categoriesSoin;
private CategoriesContrat categoriesContrat;
private CategoriesBeneficiaire categoriesBeneficiaire;

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
    this.categoriesSoin = CategoriesSoin.getInstance();
    this.categoriesContrat = CategoriesContrat.getInstance();
    this.categoriesBeneficiaire = CategoriesBeneficiaire.getInstance();
    try {
        this.contrat = categoriesContrat.trouverContrat(carTypeContrat);
        System.out.println("Contrat chargé dans évaluateur: "+this.contrat);
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
            System.out.print("Reclamation = "+reclamation+ " |  Remboursement= ");
            Remboursement remboursement;
            try {
                remboursement = calculerRemboursement(reclamation);
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

System.out.println("Reclamé: "+montantReclame);
Couverture couverture = this.contrat.trouverCouvertureParNoSoin(reclamation.getNoSoin());
System.out.println("COUVERTURE CHARGEE POUR "+contrat+" : "+couverture);
System.out.println("ON PAIE: "+onPaieCombien(montantReclame, couverture));
Remboursement remboursement = new Remboursement(reclamation.getNoSoin(), reclamation.getDateSoin(), onPaieCombien(montantReclame, couverture));
System.out.println("NOUVEAU REMBOURSEMENT: "+remboursement);
return remboursement;



   
    
}



private Argent onPaieCombien(Argent montantDemande, Couverture couverture)
    {       
        
        System.out.println("Demandé: "+montantDemande);
        System.out.println("Pourcentage: "+couverture.getPourcentage());
        montantDemande.multiplierPar(couverture.getPourcentage());

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




    public Argent getTotal() {
        return total;
    }

    public Date getMoisTraite() {
        return moisTraite;
    }

    public Contrat getContrat() {
        return contrat;
    }

    public Integer getNoClient() {
        return noClient;
    }


}