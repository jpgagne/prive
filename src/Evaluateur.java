

import java.text.SimpleDateFormat;
import java.text.DateFormat;
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
private Contrat contrat;
private Integer noClient;

Evaluateur( Integer noClient, char charContrat, Date moisTraite)
{
    this.noClient = noClient;
    this.contrat = new Contrat(charContrat);
    this.moisTraite = moisTraite;
    reclamations = new ArrayList<>();
    remboursements = new ArrayList<>();
    SimpleDateFormat formatDate = new SimpleDateFormat("MMMM yy", Locale.FRENCH);
    System.out.println("Nouvel evaluateur cree pour" + System.getProperty("line.separator")
                        + "   client: "+ noClient+ System.getProperty("line.separator")
                        + "   contrat: "+ contrat.getTypeContrat()+ System.getProperty("line.separator")
                        + "   mois: "+formatDate.format(moisTraite));
}


protected ArrayList<Remboursement> listeRemboursements()
    {
    calculerRemboursements();
    return this.remboursements;
    }
        
        
        
protected void calculerRemboursements()
    {
    total = 0.0;
    for (Iterator<Reclamation> it = reclamations.iterator(); it.hasNext();)
        {
        Reclamation reclamation = it.next();
        Remboursement remboursement = calculerRemboursement(reclamation);
        remboursements.add(remboursement);
        it.remove();
        this.total = getTotal() + remboursement.getMontant();
        }
    }

protected void ajouterReclamation(Reclamation nouvelleReclamation)
    {
    this.reclamations.add(nouvelleReclamation);
    System.out.println("Ajouté à Évaluateur: "+nouvelleReclamation);
    }


private Remboursement calculerRemboursement(Reclamation reclamation)
    {
    Remboursement remboursement;
    Double montantRembourse = -1.0;
    remboursement = new Remboursement(reclamation.getCatSoin(), reclamation.getDateSoin(), montantRembourse);
    return remboursement;
    }

    /**
     * @return the total
     */
    public Double getTotal() {
        return total;
    }

    /**
     * @return the moisTraite
     */
    public Date getMoisTraite() {
        return moisTraite;
    }

    /**
     * @return the contrat
     */
    public Contrat getContrat() {
        return contrat;
    }

    /**
     * @return the noClient
     */
    public Integer getNoClient() {
        return noClient;
    }





}