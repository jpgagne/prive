

public enum EnumCategorieSoin implements EnumConvertisseur
{   MASSOTHERAPIE(0,0), 
    OSTEOPATIE(100,100),
    PSYCHOLOGIE_INDIVIDUELLE(200,200), 
    SOINS_DENTAIRES(300,399),
    NATUROPATHIE_ACUPONCTURE(400,400),    
    CHIROPRATIE(500,500),
    PHYSIOTHERAPIE(600,600),
    ORTHOPHONIE_ERGOTHERAPIE(700,700) ; 
    

    
    private final Integer valeurPlancher;
    private final Integer valeurPlafond;
    private final Integer valeurUnaire;
    private final boolean estUnaire;
    private final boolean estIntervalle;

private EnumCategorieSoin(Integer valeurUnaire)
    {
    this.valeurUnaire = valeurUnaire;
    this.estUnaire = true;
    this.estIntervalle = false;
    this.valeurPlafond = -1;
    this.valeurPlancher = -1;
    }

    
    
 EnumCategorieSoin(Integer valeurPlancher, Integer valeurPlafond)
    {
    this.valeurPlancher = valeurPlancher;
    this.valeurPlafond = valeurPlafond;
    this.estUnaire = false;
    this.estIntervalle = true;
    this.valeurUnaire = -1;
    }

    public boolean  estUnaire()
    {
        return this.estUnaire;
    }
    
    public boolean estIntervalle()
    {
        return this.estIntervalle;
    }
    @Override


public Intervalle conversionIntervalle()  throws ExceptionIntervalle
{
if (this.estIntervalle)
{
return new Intervalle(valeurPlancher, valeurPlafond);
}
throw new ExceptionIntervalle(valeurUnaire);
}

@Override
public Integer conversionInteger() throws ExceptionIntervalle
{
if (this.estUnaire)
{
    return valeurUnaire;
}
throw new ExceptionIntervalle(new Intervalle(valeurPlancher, valeurPlafond));
}


    
    
    
    
    
    
    
}
