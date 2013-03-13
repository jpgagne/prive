

public enum EnumCategorieSoin implements EnumConvertisseur
{   MASSOTHERAPIE(0), 
    OSTEOPATIE(100),
    PSYCHOLOGIE_INDIVIDUELLE(200), 
    SOINS_DENTAIRES(300,399),
    NATUROPATHIE_ACUPONCTURE(400),    
    CHIROPRATIE(500),
    PHYSIOTHERAPIE(600),
    ORTHOPHONIE_ERGOTHERAPIE(700) ; 
    

    
    private final Integer valeurPlancher;
    private final Integer valeurPlafond;
    
  EnumCategorieSoin(Integer valeur)
    {
    this.valeurPlancher = valeur;
    this.valeurPlafond = valeur;
    }
 EnumCategorieSoin(Integer valeurPlancher, Integer valeurPlafond)
    {
    this.valeurPlancher = valeurPlancher;
    this.valeurPlafond = valeurPlafond;
    }

    
    @Override
  public Intervalle conversion() 
    {
    return new Intervalle(valeurPlancher, valeurPlafond);
    }

    
    
    
    
    
    
    
    
}
