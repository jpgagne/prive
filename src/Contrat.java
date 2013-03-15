
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Contrat
{
private char typeContrat;

private Set<Couverture> couvertures;

Contrat(char typeContrat) 
    {
    this.typeContrat = typeContrat;
    couvertures = new HashSet<>();
    }

protected void ajouterCouverture(Couverture nouvelleCouverture)
    {
    this.couvertures.add( nouvelleCouverture);
    }

protected void setSetCouvertures (Set<Couverture> couvertures)
    {
    this.couvertures = couvertures;
    }


protected boolean aCouverture(Integer noSoin)
    {
        try {
            Couverture couverture = this.trouverCouvertureParNoSoin(noSoin);
            return  true;
        } catch (ExceptionSoinNonCouvert ex) {
            return false;
        }
    }


protected Couverture trouverCouvertureParNoSoin(Integer noSoin) throws ExceptionSoinNonCouvert
    {
        Couverture couverture;
        System.out.println("nb de couvertures chargees pour contrat "+this.getTypeContrat()+" : "+couvertures.size());
         for (Iterator<Couverture> it = couvertures.iterator(); it.hasNext();) 
            {
            couverture = it.next();
            
            if (couverture.estCouvertureCherchee(noSoin))
                {
                return couverture;
                }
            }
    throw new ExceptionSoinNonCouvert(noSoin); 
   
    }

    
    
    protected char getTypeContrat()
    {
        return this.typeContrat;
    }

}
