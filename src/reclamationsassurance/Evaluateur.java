
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
    
private Contrat contrat;
private Integer noClient;

Evaluateur( Integer noClient, char contrat)
{
    this.noClient = noClient;
    this.contrat = new Contrat(contrat);
}





protected void calculerRemboursements()
{
    for (Iterator<Reclamation> it = reclamations.iterator(); it.hasNext();)
        {
        Reclamation reclamation = it.next();
        Remboursement remboursement = calculerRemboursement(reclamation);
        remboursements.add(remboursement);
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
