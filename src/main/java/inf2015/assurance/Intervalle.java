package inf2015.assurance;


import java.util.Comparator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Intervalle implements Comparable<Intervalle>
{
    
private Integer bornePlancher;
private Integer bornePlafond;
private Comparator<Intervalle> comparateur;

public Intervalle(Integer bornePlancher, Integer bornePlafond)
    {
    this.bornePlafond = bornePlafond;
    this.bornePlancher = bornePlancher;
    }

public Intervalle(Integer valeur)
    {
    this.bornePlancher = valeur;
    this.bornePlafond = valeur;
    }

public boolean inclus(Integer valeur)
    {
    return ((valeur >= getBornePlancher())&(valeur <= getBornePlafond()));
    }

public boolean estUnaire()
    {
    return (this.getBornePlafond() == this.getBornePlancher());
    }



public boolean estCoherent()
    {
    return (this.bornePlafond >= this.bornePlancher);
    }

public SortedSet<Integer> sequence()
    {
    SortedSet<Integer> set = new  TreeSet<Integer>();
    
    if (this.estCoherent())
    {
    for (int i = this.bornePlancher; i < this.bornePlafond; i++) 
        {
        set.add(i);
        }
    }
    return set;
    }




@Override
public String toString()
    {
    return "["+getBornePlancher()+".."+getBornePlafond()+"]";
    }

@Override
public boolean equals(Object autreObjet)
    {
    if (autreObjet == null) return false;
    if (autreObjet == this) return true;
    if (!(autreObjet instanceof Intervalle))return false;
    
    Intervalle autreIntervalle = (Intervalle)autreObjet;
    return ((this.getBornePlafond() - autreIntervalle.getBornePlafond() == 0)
            & this.getBornePlancher()- autreIntervalle.getBornePlancher() ==0 );
    }

    
public Integer getBornePlancher()
    {
    return bornePlancher;
    }

public Integer getBornePlafond()
    {
    return bornePlafond;
    }

public int compareTo(Intervalle o)
{

if (this.bornePlancher > o.getBornePlafond())
    {
    return 1;
    }

if (this.bornePlafond < o.getBornePlancher())
    {
    return -1;
    }

if ((this.bornePlafond == o.getBornePlafond())&(this.bornePlancher == o.getBornePlancher()))
{
    return 0;
}

try {
    throw new ExceptionIntervalle(this, o);
    } 
catch (ExceptionIntervalle ex)
    {
    throw new RuntimeException(ex); // Intégrité du programme corrompue. On crash.
    }
}

}
