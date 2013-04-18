package inf2015.assurance;

public class Couverture
{
private Soin soin;
private Double pourcentage;
private Argent valeurMax;
private  boolean aValeurMax;

public Couverture(Soin soin, Double pourcentage, Double valeurMax)
    {
    this.soin = soin;
    this.pourcentage = pourcentage;
    this.valeurMax = new Argent(valeurMax);
    this.aValeurMax = Boolean.TRUE;
    }

public Couverture(Soin soin, Double pourcentage)
    {
    this.soin = soin;
    this.pourcentage = pourcentage;
    this.valeurMax = new Argent(-1);
    this.aValeurMax = Boolean.FALSE;
    }

   
//<editor-fold defaultstate="collapsed" desc="getters">
public boolean aValeurMax()
{
    return this.aValeurMax;
}

public Argent getValeurMax()
{
    return this.valeurMax;
}

public Double getPourcentage()
{
    return pourcentage;
}

public Soin getSoin()
{
    return this.soin;
}
//</editor-fold>


public boolean estCouvertureCherchee(Integer noSoin)
    {
    return this.soin.estSoinCherche(noSoin);
    }


@Override
public String toString()
{
    StringBuilder litteral = new StringBuilder(this.soin.getIntervalleNoSoin().toString()+ " : " 
                                    +this.soin.getLitteral() + " = "
                                    +this.pourcentage.toString()+"%");
    litteral.append(", Max: ");
    if (this.aValeurMax)
        {
        litteral.append(this.valeurMax);
        }
    else{
        litteral.append("NON");
    }
    return litteral.toString();
}


}
