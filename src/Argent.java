
import java.util.Objects;


/**Class Argent
 * 
 * Supporte les valeurs comprises entre (2^31 -1) ou 2 147 483 647
 * et (2^31) ou -2 147 483 648
 * 
 * Si il vous en faut plus, alors vous êtes trop riche pour utiliser
 * cette application sans payer et elle s'interrompera sans prévenir
 */

public class Argent implements Comparable<Argent>
{

private Integer montant;

private Argent()        
    {
    }

public Argent(Integer montantCentimes)
    {
    if ((montantCentimes > Integer.MAX_VALUE/100)||(montantCentimes < Integer.MIN_VALUE))
        {
        terminerLeProgrammeAbruptement(montant);
        }
    this.montant = montantCentimes;
    }

private Argent(Double montantDouble)
    {
        // 
    }

public Argent(String montantString) throws ExceptionParseur
    {
    try {
        Double montantDouble = Double.parseDouble(montantString); 
        if ((montantDouble > Integer.MAX_VALUE/100)||(montantDouble < Integer.MIN_VALUE))
            {
            throw new ExceptionParseur("Montant lu est hors bornes +\\-MaxInt -> " + montantDouble);
            }
        this.montant = montantDouble.intValue()*100;
        }
    catch (NumberFormatException e)
        {
        throw new ExceptionParseur(montantString);
        }
    }




public Integer getMontantCentimes()
    {
    return this.montant;
    }


public Double floatValue()
    {
    return new Double(this.montant.floatValue()/100);
    }


public Argent multiplierPar(Integer multiplicateur)
    {
    return new Argent(this.montant*multiplicateur);
    }

public Argent diviserPar (Integer diviseur)
    {
    return new Argent(this.montant/diviseur);
    }

public Argent additionner (Argent valeurAdditionnee)
    {
    return new Argent(this.montant+valeurAdditionnee.getMontantCentimes());
    }

public Argent soustraire (Argent valeurSoustraite)
    {
    return new Argent(this.montant-valeurSoustraite.getMontantCentimes());
    }


@Override
public String toString()
    {
    return this.montant/100+"."+this.montant%100+" $";
    }

@Override
public int compareTo(Argent o)
    {
    return  this.getMontantCentimes().compareTo(o.getMontantCentimes());
    }

@Override
public boolean equals(Object autreObjet)
    {
    if (autreObjet.getClass() != Argent.class)
        {
        return  false; // Throw exception???
        }
    if (autreObjet == this)
        {
        return true;
        }
    Argent autreObjetCastArgent = (Argent)autreObjet;
    if (this.getMontantCentimes() == autreObjetCastArgent.getMontantCentimes())
        {
        return  true;
        }
   return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.montant);
        return hash;
    }




/**private void terminerLeProgrammeAbruptement(Integer valeur)
 * 
 * @author JP
 * 
 * Cette pseudo-exception signifie qu'une valeur dépassant +/- (MaxInt/100)
 * a été assignée à un objet de la classe Argent
 * 
 * ATTENTION : Cette pseudo-exception termine le programme abruptement;
 * pourquoi? parce que ça me tentait pas de la gérer localement partout;
 * deal with it.
 */

private void terminerLeProgrammeAbruptement(Integer valeur)
    {
    String strMontant = "INCONNU";
    if (valeur != null) 
        {
        Double FltMontant = valeur.doubleValue()/100;
        strMontant = FltMontant.toString();
        }
    
    String messageDetaille = "Montant en argent dépasse la capacité de la classe Argent"
        +" - Montant :"+strMontant+" - "
        +" dépasse les bornes permises  { "+Integer.MAX_VALUE/100+" jusqu'à "
        +Integer.MIN_VALUE/100+" }";
     throw new RuntimeException(messageDetaille);
    }


}   // end of class Argent