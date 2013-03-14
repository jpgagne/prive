
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


protected boolean aCouverture(Soin soin)
    {
        try {
            Couverture couverture = this.trouverCouverture(soin);
            return  true;
        } catch (ExceptionSoinNonCouvert ex) {
            return false;
        }
    }


protected Couverture trouverCouverture(Soin soin) throws ExceptionSoinNonCouvert
    {
        Couverture couverture;
        
         for (Iterator<Couverture> it = couvertures.iterator(); it.hasNext();) 
            {
            couverture = it.next();
            
            if (couverture.getSoin().getLitteral().equals(soin.getLitteral()))
                {
                return couverture;
                }
            }
    throw new ExceptionSoinNonCouvert(soin); 
   
    }

    
    
    protected char getTypeContrat()
    {
        return this.typeContrat;
    }

}
