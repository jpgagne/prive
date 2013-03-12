
package reclamationsassurance;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author JP
 */


public class Evaluateur 
{
    
    
private ArrayList<Reclamation> reclamations;
private ArrayList<Remboursement> remboursements;
    
private Double total;

private Contrat contrat;
private Integer noClient;

Evaluateur( Integer noClient, char charContrat)
{
    this.noClient = noClient;
    this.contrat = new Contrat(charContrat);
    System.out.println("Nouvel evaluateur cree pour client: "+ noClient
                            + " et contrat: "+ contrat.getTypeContrat());
}





protected void calculerRemboursements()
{
    total = 0.0;
    for (Iterator<Reclamation> it = reclamations.iterator(); it.hasNext();)
        {
        Reclamation reclamation = it.next();
        Remboursement remboursement = calculerRemboursement(reclamation);
        remboursements.add(remboursement);
        this.total = total + remboursement.getMontant();
        }
}

protected void ajouterReclamation(Reclamation nouvelleReclamation)
{
    this.reclamations.add(nouvelleReclamation);
}


private Remboursement calculerRemboursement(Reclamation reclamation)
{
    Remboursement remboursement;
    Double montantRembourse = -1.0;
    remboursement = new Remboursement(reclamation.getCatSoin(), reclamation.getDateSoin(), montantRembourse);
    return remboursement;
}


}
