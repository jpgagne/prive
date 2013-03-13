
public class Intervalle
{
    
private Integer bornePlancher;
private Integer bornePlafond;

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

}
