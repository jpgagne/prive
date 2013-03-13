
public class Couverture

{
    private EnumCategorieSoin categorieSoin;
    private Double pourcentage;
    private Double valeurMax;
    private  boolean aValeurMax;

public Couverture(EnumCategorieSoin categorieSoin, Double pourcentage, Double valeurMax)
    {
    this.categorieSoin = categorieSoin;
    this.pourcentage = pourcentage;
    this.valeurMax = valeurMax;
    this.aValeurMax = Boolean.TRUE;
    }

public Couverture(EnumCategorieSoin categorieSoin, Double pourcentage)
    {
    this.categorieSoin = categorieSoin;
    this.pourcentage = pourcentage;
    this.valeurMax = -1.0;
    this.aValeurMax = Boolean.FALSE;
    }

   
    
    protected boolean aValeurMax()
    {
        return this.aValeurMax;
    }
    
    protected Double getValeurMax()
    {
        return this.valeurMax;
    }
    
    
    protected Double getPourcentage()
    {
        return pourcentage;
    }
    
    protected EnumCategorieSoin getCategorieSoin()
    {
        return this.categorieSoin;
    }

    
    public String toString()
    {
        return this.categorieSoin.name();
    }
    

}
