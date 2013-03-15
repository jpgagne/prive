
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;


public class Couverture

{
    private Soin soin;
    private Double pourcentage;
    private Double valeurMax;
    private  boolean aValeurMax;

public Couverture(Soin soin, Double pourcentage, Double valeurMax)
    {
    this.soin = soin;
    this.pourcentage = pourcentage;
    this.valeurMax = valeurMax;
    this.aValeurMax = Boolean.TRUE;
    }

public Couverture(Soin soin, Double pourcentage)
    {
    this.soin = soin;
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
    
    protected Soin getSoin()
    {
        return this.soin;
    }

    
    protected boolean estCouvertureCherchee(Integer noSoin)
    {
        
        System.out.print(noSoin+" inclus?dans "+this.soin.getIntervalleNoSoin().getBornePlancher()+".."+this.soin.getIntervalleNoSoin().getBornePlancher()+ " = ");
       Boolean trouve =  ((noSoin >= this.soin.getIntervalleNoSoin().getBornePlancher())
                &(noSoin >= this.soin.getIntervalleNoSoin().getBornePlancher()));
       System.out.println(trouve);
       return trouve;
    }
    
    
    @Override
    public String toString()
    {
        StringBuilder litteral = new StringBuilder(this.soin.getIntervalleNoSoin().toString()+ " : " 
                                     +this.soin.getLitteral() + " = "
                                     +this.pourcentage.toString()+"%");
        if (this.aValeurMax)
            {
            litteral.append(", Max: ").append(this.valeurMax).append(" $");
            }
        return litteral.toString();
    }
    

}
