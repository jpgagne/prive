package reclamationsassurance;

public enum EnumCategorieSoin implements EnumConvertisseur{
    CHIROPRATIE(500),
    MASSOTHERAPIE(0), 
    NATUROPATHIE_ACUPONCTURE(400), 
    ORTHOPHONIE_ERGOTHERAPIE(700),
    OSTEOPATIE(100), 
    PHYSIOTHERAPIE(600),
    PSYCHOLOGIE_INDIVIDUELLE(200), 
    SOINS_DENTAIRES(300);
    
    private final Integer valeur;

  EnumCategorieSoin(Integer valeur)
    {
    this.valeur =  valeur;
    }

    @Override
  public Integer conversion() 
    {
    return valeur;
    }

    
    
    
    
    
    
    
    
}
