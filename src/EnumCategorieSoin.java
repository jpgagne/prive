

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
