package inf2015.assurance;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;


public class Evaluateur 
{
    
private ArrayList<Reclamation> reclamations;
private ArrayList<Remboursement> remboursements;
    
private Double total;
private Date moisTraite;
private Character carTypeContrat;
private Integer noClient;
private Contrat contrat;

private Traitement traitement;
private CategoriesSoin categoriesSoin;
private ListeContrats listeContrats;


Evaluateur( Integer noClient, char charContrat, Date moisTraite) throws ExceptionContratInexistant
    {
    this.traitement = Traitement.getInstance();
    this.categoriesSoin = CategoriesSoin.getInstance();
    this.listeContrats = ListeContrats.getInstance();
    this.carTypeContrat = charContrat;
    this.noClient = noClient;
    this.contrat = listeContrats.trouverContrat(carTypeContrat);

    this.moisTraite = moisTraite;
    reclamations = new ArrayList<Reclamation>();
    remboursements = new ArrayList<Remboursement>();
    SimpleDateFormat formatDate = new SimpleDateFormat("MMMM yy", Locale.FRENCH);
    System.out.println("Nouvel evaluateur cree pour" + System.getProperty("line.separator")
                        + "   client: "+ noClient+ System.getProperty("line.separator")
                        + "   contrat: "+ contrat.getTypeContrat()+ System.getProperty("line.separator")
                        + "   mois: "+formatDate.format(moisTraite));
}


protected ArrayList<Remboursement> listeRemboursements() throws ExceptionUsage
    {
    calculerRemboursements();
    return this.remboursements;
    }
        
        
        
protected void calculerRemboursements() throws ExceptionUsage 
    {
        System.out.println("Calculer Remboursements ()");
        total = 0.0;
        for (Iterator<Reclamation> it = reclamations.iterator(); it.hasNext();)
        {
        Reclamation reclamation = it.next();
        System.out.print("Reclamation = "+reclamation+ " |  Remboursement= ");
        Remboursement remboursement = calculerRemboursement(reclamation);
        remboursements.add(remboursement);
        
        this.total = getTotal() + remboursement.getMontant();
        }
    }

protected void ajouterReclamation(Reclamation nouvelleReclamation)
    {
    this.reclamations.add(nouvelleReclamation);
    System.out.println("Ajouté à Évaluateur: "+nouvelleReclamation);
    }


private Remboursement calculerRemboursement(Reclamation reclamation) throws ExceptionUsage 
    {
        
        System.out.println("calculerRemboursement()");
        System.out.print(" CONTRAT = " + this.contrat.getTypeContrat());
        System.out.println (reclamation);
    Remboursement remboursement;
    
    Double montantReclame = reclamation.getMontantReclame();
    
    Couverture couverture;
        try {
            couverture = this.contrat.trouverCouvertureParNoSoin(reclamation.getNoSoin());
        } catch (ExceptionSoinNonCouvert excSNC) {
            System.out.println("SOIN NON COUVERT : "+excSNC.getMessage());
            throw new ExceptionUsage(excSNC.getMessage());
        }

    remboursement = new Remboursement(reclamation.getNoSoin(), reclamation.getDateSoin(), onPaieCombien(montantReclame, couverture));
    System.out.println("NOUVEAU REMBOURSEMENT: "+remboursement);
    return remboursement;
    }



private double onPaieCombien(Double montantDemande, Couverture couverture)
    {       
    Double montantRemboursable =  montantDemande * couverture.getPourcentage();

    if (couverture.aValeurMax())
        {
        return Math.max(montantRemboursable, couverture.getValeurMax());
        }
    return montantRemboursable;
    }





public void setContrat(Contrat contrat)
{
    this.contrat = contrat;
}

public void setContrat(Character charContrat) throws ExceptionContratInexistant
    {  
    this.contrat = listeContrats.trouverContrat(carTypeContrat);
    
    }



    public Double getTotal() {
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